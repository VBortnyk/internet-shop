package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.interfaces.ProductDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    public static final String COLUMN = "product_id";

    @Override
    public Product create(Product product) {
        Connection connection = ConnectionUtil.getConnection();
        String query = "INSERT INTO products (name, price) VALUES (?, ?);";
        try (PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getLong(COLUMN));
            }
            return product;
        } catch (SQLException ex) {
            throw new DataProcessingException(
                    "Creation failed. Incorrect data or the product already exists", ex);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        Connection connection = ConnectionUtil.getConnection();
        String query = "SELECT * FROM products WHERE product_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Product product = getProductFromResultSet(result);
                return Optional.of(product);
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new DataProcessingException(
                    "Incorrect data or product with id " + id + " is absent in the DB", ex);
        }
    }

    @Override
    public List<Product> getAll() {
        Connection connection = ConnectionUtil.getConnection();
        String query = "SELECT * FROM products;";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Product product = getProductFromResultSet(result);
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            throw new DataProcessingException("Query failed", ex);
        }
    }

    @Override
    public Product update(Product product) {
        Connection connection = ConnectionUtil.getConnection();
        String query = "UPDATE products SET name=?, price=? WHERE product_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setLong(3, product.getId());
            ResultSet result = statement.executeQuery();
            return product;
        } catch (SQLException ex) {
            throw new DataProcessingException("No corresponding products to update", ex);
        }
    }

    @Override
    public boolean delete(Long id) {
        Connection connection = ConnectionUtil.getConnection();
        String query = "DELETE FROM products WHERE product_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DataProcessingException("Deleting failed", ex);
        }
    }

    public Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product(resultSet.getString("product_name"),
                resultSet.getDouble("product_price"));
        product.setId((resultSet.getLong("product_id")));
        return product;
    }
}
