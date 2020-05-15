package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import mate.academy.internetshop.dao.interfaces.UserDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class UserDaoJdbcImpl implements UserDao {

    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM users WHERE login = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return Optional.of(getUserFromResultSet(result));
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new DataProcessingException("No users found with login: " + login, ex);
        }
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users(name, login, password) VALUES(?, ?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement
                    = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            while (result.next()) {
                user.setId(result.getLong(1));
            }
            addRoles(user);
            return user;

        } catch (SQLException ex) {
            throw new DataProcessingException("New user creating failed", ex);
        }
    }

    @Override
    public Optional<User> get(Long userId) {
        String query = "SELECT * FROM users WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return Optional.of(getUserFromResultSet(result));
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to get user with id: " + userId, ex);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                userList.add(getUserFromResultSet(result));
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to get the list of users", ex);
        }
        return userList;
    }

    @Override
    public User update(User user) {
        deleteUserFromUsersRoles(user.getId());
        addRoles(user);
        String query = "UPDATE users SET  name = ?, login = ?, password = ? "
                + "WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
            return user;
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to update user", ex);
        }
    }

    @Override
    public boolean delete(Long userId) {
        String query = "DELETE FROM users WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            deleteUserFromUsersRoles(userId);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to delete user", ex);
        }

    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User(
                resultSet.getLong("user_id"),
                resultSet.getString("name"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                getUserRoles(resultSet.getLong("user_id")));
        return user;
    }

    private Set<Role> getUserRoles(Long userId) {
        Set<Role> roles = new HashSet<>();
        String query = "SELECT * From users_roles"
                + " JOIN roles ON users_roles.role_id = roles.role_id"
                + " WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(userId));
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Role role = Role.of(result.getString("role_name"));
                roles.add(role);
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't get roles from user with id: " + userId, ex);
        }
        return roles;
    }

    private void addRoles(User user) {
        String selectRoleIdQuery = "SELECT role_id FROM roles WHERE role_name = ?";
        String insertUsersRolesQuery = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement(selectRoleIdQuery);
            for (Role role : user.getRoles()) {
                selectStatement.setString(1, role.getRoleName().name());
                ResultSet resultSet = selectStatement.executeQuery();
                resultSet.next();
                PreparedStatement insertStatement =
                        connection.prepareStatement(insertUsersRolesQuery);
                insertStatement.setLong(1, user.getId());
                insertStatement.setLong(2, resultSet.getLong("role_id"));
                insertStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to add roles to user", ex);
        }
    }

    private void deleteRole(Long userId, Long roleId) {
        String query = "DELETE FROM users_roles WHERE role_id = ? AND user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, roleId);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to delete the role from the user", ex);
        }
    }

    private void createRole(String roleName) {
        String query = "INSERT INTO roles(role_name) VALUES (?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, roleName);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to add role to the DB", ex);
        }
    }

    private void deleteUserFromUsersRoles(Long userId) {
        String deleteUserQuery = "DELETE FROM users_roles WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteUserQuery);
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataProcessingException("Failed to delero user's roles", ex);
        }
    }
}
