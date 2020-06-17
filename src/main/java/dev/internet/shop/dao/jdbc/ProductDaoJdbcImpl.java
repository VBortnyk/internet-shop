package dev.internet.shop.dao.jdbc;

import dev.internet.shop.dao.ProductDao;
import dev.internet.shop.exceptions.DataProcessingException;
import dev.internet.shop.lib.Dao;
import dev.internet.shop.model.Product;
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
public class ProductDaoJdbcImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        String query = "INSERT INTO products (name, price) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement
                    = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getLong(1));
            }
            return product;
        } catch (SQLException ex) {
            throw new DataProcessingException(
                    "Creation failed. Incorrect data or the product already exists", ex);
        }
    }

    @Override
    public Optional<Product> get(Long id) {

        String query = "SELECT * FROM products WHERE product_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
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
        String query = "SELECT product_id, name, price FROM products;";
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
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

        String query = "UPDATE products SET name=?, price=? WHERE product_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setLong(3, product.getId());
            return product;
        } catch (SQLException ex) {
            throw new DataProcessingException("No corresponding products to update", ex);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM products WHERE product_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DataProcessingException("Deleting failed", ex);
        }
    }

    public Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getLong("product_id"),
                resultSet.getString("name"),
                resultSet.getDouble("price"));
    }
}
