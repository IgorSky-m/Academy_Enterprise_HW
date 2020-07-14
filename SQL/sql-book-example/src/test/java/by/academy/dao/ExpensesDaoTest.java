package by.academy.dao;

import by.academy.dto.ExpensesDto;
import by.academy.factories.DaoFactory;
import by.academy.interfaces.IDao;
import by.academy.sql.MySQLDataSource;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.*;

import java.sql.Date;
import java.sql.SQLException;

public class ExpensesDaoTest {
    IDao dao;
    IDatabaseConnection connection;
    @Before
    public void setUp(){
        try {
            dao = DaoFactory.getDao("expenses_test");
            connection= new MySqlConnection(MySQLDataSource.getTestConnection(),"expenses_test_data");
        } catch (SQLException | DatabaseUnitException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create() throws DatabaseUnitException, SQLException {
        ExpensesDto expensesDto = new ExpensesDto();
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(ExpensesDaoTest.class.getResourceAsStream("ExpensesDao.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection,dataSet);
        expensesDto.setId(6);
        expensesDto.setDate(Date.valueOf("2020-02-04"));
        expensesDto.setReceiver(2);
        expensesDto.setValue(2.345);
        int i = dao.create(expensesDto);
        Assert.assertEquals(6,i);
        DatabaseOperation.DELETE.execute(connection,dataSet);
    }
}
