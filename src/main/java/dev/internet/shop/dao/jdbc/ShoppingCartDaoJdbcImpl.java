package dev.internet.shop.dao.jdbc;

import dev.internet.shop.dao.ShoppingCartDao;
import dev.internet.shop.exceptions.DataProcessingException;
import dev.internet.shop.lib.Dao;
import dev.internet.shop.model.Product;
import dev.internet.shop.model.ShoppingCart;
import dev.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart cart) {
        String query = "INSERT INTO shopping_carts (user_id) values (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, cart.getUserId());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                cart.setId(result.getLong(1));
            }
            return cart;
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to create shoppingCart", ex);
        }
    }

    @Override
    public Optional<ShoppingCart> get(Long cartId) {
        String query = "SELECT * FROM shopping_carts WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, cartId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return Optional.of(getCartFromResultSet(result));
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to get cart by id: " + cartId, ex);
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        List<ShoppingCart> cartsList = new ArrayList<>();
        String query = "SELECT * FROM shopping_carts;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                cartsList.add(getCartFromResultSet(result));
            }
            return cartsList;
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to get all shopping carts", ex);
        }
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        String query = "SELECT * FROM shopping_carts WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return Optional.of(getCartFromResultSet(result));
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to get shopping cart by user id", ex);
        }
    }

    @Override
    public List<Product> getAllProducts(Long cartId) {
        String query = "SELECT products.product_id, name, price\n"
                + "FROM shopping_carts_products\n"
                + "JOIN products\n"
                + "ON shopping_carts_products.product_id = products.product_id\n"
                + "WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, cartId);
            ResultSet result = statement.executeQuery();
            List<Product> productList = new ArrayList<>();
            while (result.next()) {
                Product product = new Product(
                        result.getLong("product_id"),
                        result.getString("name"),
                        result.getDouble("price"));
                productList.add(product);
            }
            return productList;
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to get products from shopping cart", ex);

        }
    }

    @Override
    public ShoppingCart update(ShoppingCart cart) {
        deleteProductsFromCart(cart);
        addProductsToCart(cart);
        return cart;
    }

    @Override
    public boolean delete(Long cartId) {
        String query = "DELETE FROM shopping_carts WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, cartId);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DataProcessingException(
                    "Failed to delete shopping cart with id: " + cartId, ex);
        }
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return delete(shoppingCart.getId());
    }

    private ShoppingCart getCartFromResultSet(ResultSet result) throws SQLException {
        Long cartId = result.getLong("cart_id");
        Long userId = result.getLong("user_id");
        List<Product> products = getAllProducts(cartId);
        return new ShoppingCart(cartId, userId, products);
    }

    private void deleteProductsFromCart(ShoppingCart shoppingCart) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM internet_shop.shopping_carts_products WHERE cart_id=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCart.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete products from shopping cart", e);
        }
    }

    private void addProductsToCart(ShoppingCart shoppingCart) {
        String query = "INSERT INTO internet_shop.shopping_carts_products(cart_id, product_id) "
                + "values(?,?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : shoppingCart.getProducts()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, shoppingCart.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add products to shopping cart", e);
        }
    }
}
