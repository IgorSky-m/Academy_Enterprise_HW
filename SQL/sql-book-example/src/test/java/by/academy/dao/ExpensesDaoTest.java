package by.academy.dao;

import by.academy.dto.ExpensesDto;
import by.academy.factories.DaoFactory;
import by.academy.interfaces.IDao;
import by.academy.sql.MySQLDataSource;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ExpensesDaoTest {
    IDao<ExpensesDto> dao;
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


    @After
    public void tearDown() throws Exception {
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
        assertEquals(6,i);
        DatabaseOperation.DELETE.execute(connection,dataSet);
    }

    @Test
    public void read() throws SQLException, DatabaseUnitException {
        Integer validId = 1;
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(ExpensesDaoTest.class.getResourceAsStream("ExpensesDao.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection,dataSet);
        ExpensesDto expensesDtoRead = dao.read(validId);
        assertEquals(validId,expensesDtoRead.getId());

        DatabaseOperation.DELETE.execute(connection,dataSet);
    }


    @Test
    public void readAll() throws DatabaseUnitException, SQLException {
        int validListSize = 3;
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(ExpensesDaoTest.class.getResourceAsStream("ExpensesDao.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection,dataSet);
        List<ExpensesDto> expensesDtoList = dao.readAll();
        assertEquals(validListSize,expensesDtoList.size());

        DatabaseOperation.DELETE.execute(connection,dataSet);

    }

    @Test
    public void update() throws DatabaseUnitException, SQLException {
        Integer id = 2;
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(ExpensesDaoTest.class.getResourceAsStream("ExpensesDao.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection,dataSet);
        ExpensesDto expensesDtoUpdate = new ExpensesDto();
        expensesDtoUpdate.setId(id);
        expensesDtoUpdate.setDate(Date.valueOf("2020-02-10"));
        expensesDtoUpdate.setReceiver(34);
        expensesDtoUpdate.setValue(500.25234);
        ExpensesDto expensesDtoBefore = dao.read(id);
        assertNotEquals(expensesDtoUpdate,expensesDtoBefore);

        dao.update(expensesDtoUpdate);
        ExpensesDto expensesDtoAfter = dao.read(id);
        assertEquals(expensesDtoUpdate,expensesDtoAfter);
        assertNotEquals(expensesDtoBefore,expensesDtoAfter);

        DatabaseOperation.DELETE.execute(connection,dataSet);

    }

    @Test
    public void delete() throws DatabaseUnitException, SQLException {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(ExpensesDaoTest.class.getResourceAsStream("ExpensesDao.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection,dataSet);
        Integer id = 3;
        dao.delete(id);

        assertThrows(InvalidParameterException.class,()-> {
            dao.read(id);
        });

        DatabaseOperation.DELETE.execute(connection,dataSet);

    }

    @Test
    public void readSqlParam() {


    }
}