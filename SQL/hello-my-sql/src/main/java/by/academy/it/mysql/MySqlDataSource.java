package by.academy.it.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlDataSource {
    private static final Properties properties = new Properties();
    private static final String URL = "jdbc:mysql://localhost:3306/client";
    private static final String TEST_URL =  "jdbc:mysql://localhost:3306/client_test";
    static {
        properties.put("user", "root");
        properties.put("password", "root");
        properties.put("useSSL", "false");
        properties.put("serverTimezone", "UTC");
        properties.put("charset", "UTF8");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,properties);
    }



    public static Connection getTestConnection () throws SQLException {
        return DriverManager.getConnection(TEST_URL,properties);
    }






}
