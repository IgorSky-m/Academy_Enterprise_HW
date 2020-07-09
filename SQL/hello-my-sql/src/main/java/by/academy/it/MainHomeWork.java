package by.academy.it;


import java.lang.reflect.Parameter;
import java.sql.*;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class MainHomeWork {


    public static void main(String[] args) {
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        String string = date.toString();
        System.out.println(string);

        Properties properties = new Properties();
        properties.put("user","root");
        properties.put("password","root");
        properties.put("useSSL","false");
        properties.put("serverTimezone","UTC");
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/listexpenses", properties);
            Statement statement = connection.createStatement();



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
