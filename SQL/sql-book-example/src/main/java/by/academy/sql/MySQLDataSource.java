package by.academy.sql;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDataSource {
    private static final Properties PROPERTIES = new Properties();
    private static final String WORK_URL = "jdbc:mysql://localhost:3306/expenses_data";
    private static final String TEST_URL = "jdbc:mysql://localhost:3306/expenses_test_data";
    static {
        PROPERTIES.put("user","root");
        PROPERTIES.put("password","root");
        PROPERTIES.put("useSSL","false");
        PROPERTIES.put("serverTimezone","UTC");
        PROPERTIES.put("charset","UTF-8");
    }
    private MySQLDataSource(){}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(WORK_URL,PROPERTIES);
    }

    public static Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection(TEST_URL,PROPERTIES);
    }
}
