package by.academy.it.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlDataSource {
    static Properties properties = new Properties();
    static String url = "jdbc:mysql://localhost:3306/client";
    static {
        properties.put("user", "root");
        properties.put("password", "root");
        properties.put("useSSL", "false");
        properties.put("serverTimezone", "UTC");
        properties.put("charset", "UTF8");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,properties);
    }




}
