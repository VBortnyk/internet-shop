package dev.internet.shop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL driver not found");
        }
    }

    public static Connection getConnection() {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "mate2020");
        String url = "jdbc:mysql://localhost:3306/internet_shop?serverTimezone=UTC";
        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Incorrect address or the resource is not available", ex);
        }
    }
}
