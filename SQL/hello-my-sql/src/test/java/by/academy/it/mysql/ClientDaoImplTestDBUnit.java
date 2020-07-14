package by.academy.it.mysql;

import by.academy.it.ClientDaoFactory;
import by.academy.it.ClientDto;
import by.academy.it.interfaces.IClientDao;
import by.academy.it.interfaces.IClientDto;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ClientDaoImplTestDBUnit {
    private IClientDao clientDao;
    private IDatabaseConnection connection;

    @Before
    public void setUp() throws Exception {
        try {
            clientDao = ClientDaoFactory.getClientDao("mysql_test");
            connection = new MySqlConnection(MySqlDataSource.getTestConnection(), "client_test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getLastId() throws DatabaseUnitException, SQLException {

        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(ClientDaoImplTestDBUnit.class.getResourceAsStream("ClientDaoImpl.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);

        //When
        int maxId = clientDao.getLastId();

        //Then
        assertEquals(5,maxId);
        DatabaseOperation.DELETE.execute(connection, dataSet);

    }

    @Test
    public void read() throws DatabaseUnitException, SQLException {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(ClientDaoImplTestDBUnit.class.getResourceAsStream("ClientDaoImpl.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection,dataSet);
        IClientDto validClient2 = new ClientDto();
        validClient2.setId(2);
        validClient2.setName("name2");
        validClient2.setSecondName("second_name2");
        validClient2.setEmail("email2@gmail.com");
        validClient2.setDateOfBirth(Date.valueOf("1990-01-02"));
        validClient2.setGender('N');

        //when
        IClientDto readClient2 = clientDao.read(2);

        //then
        assertEquals(validClient2,readClient2);
        DatabaseOperation.DELETE.execute(connection,dataSet);
    }

}
