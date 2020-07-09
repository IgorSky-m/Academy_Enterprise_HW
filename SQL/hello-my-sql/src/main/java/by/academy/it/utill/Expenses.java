package by.academy.it.utill;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

public class Expenses {

    public static void addRandomExpense(Statement statement, int amount) throws SQLException {
        if (amount < 0) throw new IllegalArgumentException("negative value");
        int amountValue = 0;
        int lastOperationIndex = 0;
        Random random = new Random();
        ResultSet resultSet = statement.executeQuery("SELECT num FROM listexpenses.receivers WHERE num=(SELECT MAX(num) from receivers)");
        int col = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
               amountValue = (resultSet.getInt(col));
        }
        resultSet = statement.executeQuery("SELECT num FROM listexpenses.expenses WHERE num=(SELECT MAX(num) FROM expenses)");
        while (resultSet.next()) {
            col = resultSet.getMetaData().getColumnCount();
            lastOperationIndex = resultSet.getInt(col);
        }

        for (int i = 0; i < amount; i++) {
            statement.execute("insert into expenses values ("+
                    ++lastOperationIndex +",'"+
                    new java.sql.Date(System.currentTimeMillis()-(Math.abs(random.nextLong()/10_000_000)))+"',"+
                    Math.abs(random.nextInt(amountValue))+","+
                    new BigDecimal(Math.random()*random.nextInt(10000)).setScale(2, RoundingMode.HALF_EVEN)+")"
            );
        }
    }

    public void addExpense(Statement statement,String date, int receiver, double value ) throws SQLException {
        int col = 0;
        int lastOperationIndex = 0;
        ResultSet resultSet = statement.executeQuery("SELECT num FROM listexpenses.expenses WHERE num=(SELECT MAX(num) FROM expenses)");
        while (resultSet.next()) {
            col = resultSet.getMetaData().getColumnCount();
            lastOperationIndex = resultSet.getInt(col);
        }
        statement.execute(
                "INSERT INTO expenses values (" +
                        lastOperationIndex + ",'"+
                        date +"',"+
                        receiver +","+
                        value+")"
        );

    }
    public void addExpense (Statement statement,Date date, int receiver, double value) throws SQLException {
        int col = 0;
        int lastOperationIndex = 0;
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        ResultSet resultSet = statement.executeQuery("SELECT num FROM listexpenses.expenses WHERE num=(SELECT MAX(num) FROM expenses)");
        while (resultSet.next()) {
            col = resultSet.getMetaData().getColumnCount();
            lastOperationIndex = resultSet.getInt(col);
        }
        statement.execute(
                "INSERT INTO expenses values (" +
                        lastOperationIndex + ",'"+
                        sqlDate +"',"+
                        receiver +","+
                        value+")"
        );

    }

    public void addExpense (Statement statement, int receiver, double value) throws SQLException {
        int col = 0;
        int lastOperationIndex = 0;
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        ResultSet resultSet = statement.executeQuery("SELECT num FROM listexpenses.expenses WHERE num=(SELECT MAX(num) FROM expenses)");
        while (resultSet.next()) {
            col = resultSet.getMetaData().getColumnCount();
            lastOperationIndex = resultSet.getInt(col);
        }
        statement.execute(
                "INSERT INTO expenses values (" +
                        lastOperationIndex + ",'"+
                        sqlDate +"',"+
                        receiver +","+
                        value+")"
        );

    }

}
