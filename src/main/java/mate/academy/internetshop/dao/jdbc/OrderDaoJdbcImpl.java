package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.interfaces.OrderDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public boolean delete(Order order) {
        return delete(order.getId());
    }

    @Override
    public boolean delete(Long orderId) {
        String query = "DELETE FROM orders where order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to delete order", ex);
        }
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        String query = "SELECT * FROM orders WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet result = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (result.next()) {
                orders.add(getOrderFromResultSet(result));
            }
            return orders;
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to get user orders", ex);
        }
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) values(?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            addProductsToOrder(order);
            return order;
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to create order", ex);
        }
    }

    @Override
    public Optional<Order> get(Long orderId) {
        String query = "SELECT * from orders where order_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getOrderFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to get order by id", ex);
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DataProcessingException("Getting of list of orders is failed", e);
        }
    }

    @Override
    public Order update(Order order) {
        deleteProductsFromOrder(order);
        addProductsToOrder(order);
        return order;
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order(
                resultSet.getLong("order_id"),
                resultSet.getLong("user_id"),
                getProductsFromOrder(resultSet.getLong("order_id")));
        return order;
    }

    private List<Product> getProductsFromOrder(Long orderId) {
        String query = "SELECT products.product_id, products.name, products.price "
                + "FROM orders_products INNER JOIN products "
                + "ON  orders_products.product_id=products.product_id \n"
                + "WHERE orders_products.order_id = ?;";
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("product_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"));
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            throw new DataProcessingException(
                    "Failed to get list of products from order", ex);
        }
    }

    private void addProductsToOrder(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : order.getProducts()) {
                String query = "INSERT INTO orders_products(order_id, product_id) "
                        + "values(?,?);";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, order.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add products to order", e);
        }
    }

    private void deleteProductsFromOrder(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM orders_products WHERE order_id=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataProcessingException(
                    "Failed to delete products from order", ex);
        }
    }
}
