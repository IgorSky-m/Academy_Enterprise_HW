package by.academy.it;

import by.academy.it.utill.Expenses;
import java.sql.*;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("log");
        Random random = new Random();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.put("user","root");
            properties.put("password","root");
            properties.put("useSSL","false");
            properties.put("serverTimezone","UTC");
            properties.put("charset","UTF8");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/listexpenses",properties);
            logger.log(new LogRecord(Level.INFO,"MySQL connection: "+ !connection.isClosed()));
            Statement statement = connection.createStatement();
            Expenses.addRandomExpense(statement,1);


//            int length= GenerateLists.getListEngNamesLength()/2;
//            int counter = 1;
//            for (String s : GenerateLists.getEngNamesList()) {
//                counter++;
//                if ((counter/2) !=0) {
//                    statement.execute("insert into receivers values (" +
//                            counter + ",'" +
//                            s + "')");
//                }
//            }





        } catch (ClassNotFoundException | SQLException e) {
            logger.log(new LogRecord(Level.WARNING,e.getMessage()));
            e.printStackTrace();
        } finally {
            logger.log(new LogRecord(Level.INFO,"finish"));
        }

    }
}
//            for (int i = 1; i <= 5; i++) {
//                Student student = GenerateLists.generateRandomStudent();
//                System.out.println(student.toString());
//                statement.execute("insert into studenttable(ID,studentName,studentSurname,age,gender,course,specialization) " +
//                        "values (" +
//                        i+",'"+
//                        student.getName()+"','"+
//                        student.getSurname()+"',"+
//                        student.getAge()+",'"+
//                        student.getGender()+"',"+
//                        student.getCourse()+",'"+
//                        student.getSpecialization()+"')"
//                );
//
//            }