package by.academy.it.mysql;

import by.academy.it.ClientDaoFactory;
import by.academy.it.ClientDto;
import by.academy.it.exceptions.InvalidCreationException;
import by.academy.it.interfaces.IClientDao;
import by.academy.it.interfaces.IClientDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
*@param clients id between 350 and 362 - reserved for read tests
*@param client id 200 created for upgrade tests
*@param client id 201 created for delete tests
*
 */

import static org.junit.Assert.*;

public class ClientDaoImplTest {

    private IClientDao clientDao;
    private int clientId;
    int countDataBaseRecords;



    @Before
    public void setUp() throws Exception {

        this.clientId = 0;
        try {
            clientDao = ClientDaoFactory.getClientDao("mysql_test");
            MySqlDataSource.getTestConnection().createStatement()
                    .execute("INSERT INTO client_test.clients values (200,'testUpdateName'," +
                            "'testUpdateSecondName','test@mail.ru','1980-02-25','F')");
            MySqlDataSource.getTestConnection().createStatement()
                    .execute("INSERT INTO client_test.clients values (201,'testDeleteName'," +
                            "'testDeleteSecondName','test@mail.ru','1980-02-25','F')");
            ResultSet resultSet = MySqlDataSource.getTestConnection().createStatement()
                    .executeQuery("SELECT count(id) from client_test.clients");
            if (resultSet.next()) this.countDataBaseRecords = resultSet.getInt(resultSet.getRow());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws SQLException {
        MySqlDataSource.getTestConnection().createStatement()
                .execute("DELETE FROM client_test.clients WHERE id NOT BETWEEN 350 and 362");
    }


    //create
    @Test
    public void createValidClient() throws SQLException {
        IClientDto client = new ClientDto();

        long dateMillis = System.currentTimeMillis();
        client.setId(++ this.clientId);
        client.setName("testName");
        client.setSecondName("testSecondName");
        client.setEmail("test@test.com");
        client.setDateOfBirth(new Date(dateMillis));
        client.setGender('M');

        assertEquals(clientDao.create(client),this.clientId);

    }

    @Test
    public void createClientWithNullID() throws SQLException {
        IClientDto client = new ClientDto();
        long dateMillis = System.currentTimeMillis();
        client.setId(null);
        client.setName("testName");
        client.setSecondName("testSecondName");
        client.setEmail("test@test.com");
        client.setDateOfBirth(new Date(dateMillis));
        client.setGender('M');
        assertThrows("ID can't be null",InvalidParameterException.class,() -> {
            clientDao.create(client);
        });



    }

    @Test
    public void createClientWithNegativeID() throws SQLException {
        IClientDto client = new ClientDto();
        long dateMillis = System.currentTimeMillis();
        client.setId(-1);
        client.setName("testName");
        client.setSecondName("testSecondName");
        client.setEmail("test@test.com");
        client.setDateOfBirth(new Date(dateMillis));
        client.setGender('M');
        assertThrows("ID can't be negative",InvalidParameterException.class,() -> {
            clientDao.create(client);
        });



    }

    @Test
    public void createClientWithZeroID() throws SQLException {
        IClientDto client = new ClientDto();
        long dateMillis = System.currentTimeMillis();
        client.setId(0);
        client.setName("testName");
        client.setSecondName("testSecondName");
        client.setEmail("test@test.com");
        client.setDateOfBirth(new Date(dateMillis));
        client.setGender('M');
        assertThrows("ID can't be equals '0'", InvalidParameterException.class, () -> {
            clientDao.create(client);
        });
    }

    @Test
    public void createClientDuplicate() throws SQLException {
            IClientDto client1 = new ClientDto();
            IClientDto client2 = new ClientDto();


            long dateMillis = System.currentTimeMillis();
            //client1
            client1.setId(++ this.clientId);
            client1.setName("testName");
            client1.setSecondName("testSecondName");
            client1.setEmail("test@test.com");
            client1.setDateOfBirth(new Date(dateMillis));
            client1.setGender('M');
            //client2
            client2.setId(clientId);
            client2.setName("testName");
            client2.setSecondName("testSecondName");
            client2.setEmail("test@test.com");
            client2.setDateOfBirth(new Date(dateMillis));
            client2.setGender('M');
            int client1id = clientDao.create(client1);
            int client2id = clientDao.create(client2);

            assertEquals(0,client2id);
    }

    @Test
    public void createClientIfClientEqualsNull(){
        assertThrows("creation is unsuccessful. client can't be null",InvalidCreationException.class,()-> {
            IClientDto client = null;
            clientDao.create(client);
        });


    }

    @Test
    public void createClientWithNullName() throws SQLException {
        IClientDto client = new ClientDto();

        long dateMillis = System.currentTimeMillis();
        client.setId(++ this.clientId);
        client.setName(null);
        client.setSecondName("testSecondName");
        client.setEmail("test@test.com");
        client.setDateOfBirth(new Date(dateMillis));
        client.setGender('M');

        assertEquals(clientDao.create(client),this.clientId);

    }

    @Test
    public void createClientWithNullSecondName() throws SQLException {
        IClientDto client = new ClientDto();

        long dateMillis = System.currentTimeMillis();
        client.setId(++ this.clientId);
        client.setName("testName");
        client.setSecondName(null);
        client.setEmail("test@test.com");
        client.setDateOfBirth(new Date(dateMillis));
        client.setGender('M');

        assertEquals(clientDao.create(client),this.clientId);

    }

    // ПЕРЕДЕЛАТЬ!
    @Test
    public void createClientWithNullEmail() throws SQLException {
        IClientDto client = new ClientDto();

        long dateMillis = System.currentTimeMillis();
        client.setId(++ this.clientId);
        client.setName("testName");
        client.setSecondName("testSecondName");
        client.setEmail(null);
        client.setDateOfBirth(new Date(dateMillis));
        client.setGender('M');

        assertEquals(clientDao.create(client),this.clientId);

    }

    @Test
    public void createClientWithIncorrectEmail(){
        IClientDto validClient = new ClientDto();
        validClient.setId(++clientId);
        validClient.setName("testNoSecondNameName");
        validClient.setSecondName(null);
        validClient.setEmail("testMail");
        validClient.setDateOfBirth(Date.valueOf("1990-11-20"));
        validClient.setGender('M');
        assertThrows("incorrect email",InvalidParameterException.class,()-> {
            clientDao.create(validClient);
        });

    }

    @Test
    public void createClientWithNullDate() throws SQLException {
        IClientDto client = new ClientDto();

        long dateMillis = System.currentTimeMillis();
        client.setId(++ this.clientId);
        client.setName("testName");
        client.setSecondName("testSecondName");
        client.setEmail("test@test.com");
        client.setDateOfBirth(null);
        client.setGender('M');

        assertEquals(clientDao.create(client),this.clientId);

    }

    @Test
    public void createClientWithNullGender() throws SQLException {
        IClientDto client = new ClientDto();

        long dateMillis = System.currentTimeMillis();
        client.setId(++ this.clientId);
        client.setName("testName");
        client.setSecondName("testSecondName");
        client.setEmail("test@test.com");
        client.setDateOfBirth(new Date(dateMillis));
        client.setGender(null);

        assertEquals(clientDao.create(client),this.clientId);

    }

    //read
//    @Test
//    public void readValidClient() throws SQLException {
//        IClientDto validClient = new ClientDto();
//        validClient.setId(357);
//        validClient.setName("testForReadName");
//        validClient.setSecondName("testForReadSurname");
//        validClient.setEmail("testMail@gmail.com");
//        validClient.setDateOfBirth(Date.valueOf("1990-11-20"));
//        validClient.setGender('M');
//        IClientDto clientRead = clientDao.read(357);
//        assertEquals(validClient,clientRead);
//
//    }
//
//    @Test
//    public void readClientWithNullGender() throws SQLException {
//        IClientDto validClient = new ClientDto();
//        validClient.setId(358);
//        validClient.setName("testNoGenderName");
//        validClient.setSecondName("testNoGenderSurname");
//        validClient.setEmail("testNoGenderMail@gmail.com");
//        validClient.setDateOfBirth(Date.valueOf("1990-11-20"));
//        validClient.setGender(null);
//        IClientDto clientRead = clientDao.read(358);
//        assertEquals(validClient,clientRead);
//
//    }
//
//    @Test
//    public void readClientWithNullDateOfBirth() throws SQLException {
//        IClientDto validClient = new ClientDto();
//        validClient.setId(359);
//        validClient.setName("testNoDateOfBirhtName");
//        validClient.setSecondName("testNoDateOfBirhtSurname");
//        validClient.setEmail("testNoDateOfBirhtMail@gmail.com");
//        validClient.setDateOfBirth(null);
//        validClient.setGender('F');
//        IClientDto clientRead = clientDao.read(359);
//        assertEquals(validClient,clientRead);
//    }
//
//    @Test
//    public void readClientWithNullEmail() throws SQLException {
//        IClientDto validClient = new ClientDto();
//        validClient.setId(360);
//        validClient.setName("testNoEmailName");
//        validClient.setSecondName("testNoEmailSurname");
//        validClient.setEmail(null);
//        validClient.setDateOfBirth(Date.valueOf("1990-11-20"));
//        validClient.setGender('F');
//        IClientDto clientRead = clientDao.read(360);
//        assertEquals(validClient,clientRead);
//
//    }
//
//    @Test
//    public void readClientWithNullSecondName() throws SQLException {
//        IClientDto validClient = new ClientDto();
//        validClient.setId(361);
//        validClient.setName("testNoSecondNameName");
//        validClient.setSecondName(null);
//        validClient.setEmail("testNoSecondNameMail@gmail.com");
//        validClient.setDateOfBirth(Date.valueOf("1990-11-20"));
//        validClient.setGender('M');
//        IClientDto clientRead = clientDao.read(361);
//        assertEquals(validClient,clientRead);
//
//    }
//
//    @Test
//    public void readClientWithNullName() throws SQLException {
//        IClientDto validClient = new ClientDto();
//        validClient.setId(362);
//        validClient.setName(null);
//        validClient.setSecondName("testNoNameSurname");
//        validClient.setEmail("testNoNameMail@gmail.com");
//        validClient.setDateOfBirth(Date.valueOf("1990-11-20"));
//        validClient.setGender('N');
//        IClientDto clientRead = clientDao.read(362);
//        assertEquals(validClient,clientRead);
//
//    }
//
//    @Test
//    public void readClientWithAllNullParameters() throws SQLException {
//        IClientDto validClient = new ClientDto();
//        validClient.setId(356);
//        validClient.setName(null);
//        validClient.setSecondName(null);
//        validClient.setEmail(null);
//        validClient.setDateOfBirth(null);
//        validClient.setGender(null);
//        IClientDto clientRead = clientDao.read(356);
//        assertEquals(validClient,clientRead);
//
//    }

    @Test
    public void readNotExistRecord(){
        assertThrows("record does't exist",InvalidParameterException.class,()->{
            clientDao.read(999999999);
        });
    }

    @Test
    public void readNegativeId(){
        assertThrows("ID can't be negative",InvalidParameterException.class,() -> {
            clientDao.read(-1);
        });
    }

    @Test
    public void readZeroId(){
        assertThrows("ID can't be equals '0'", InvalidParameterException.class, () -> {
            clientDao.read(0);
        });
    }

    @Test
    public void readNullId(){
        assertThrows("ID can't be null",InvalidParameterException.class,() -> {
           clientDao.read(null);
        });

    }

//    //readAll
//    @Test
//    public void readAll() throws SQLException {
//
//        List<IClientDto> actualClientList = clientDao.readAll();
//
//        //nullClient
//        IClientDto nullClient = new ClientDto();
//        nullClient.setId(356);
//        nullClient.setName(null);
//        nullClient.setSecondName(null);
//        nullClient.setEmail(null);
//        nullClient.setDateOfBirth(null);
//        nullClient.setGender(null);
//
//        //nullGenderClient
//        IClientDto nullGenderClient = new ClientDto();
//        nullGenderClient.setId(358);
//        nullGenderClient.setName("testNoGenderName");
//        nullGenderClient.setSecondName("testNoGenderSurname");
//        nullGenderClient.setEmail("testNoGenderMail@gmail.com");
//        nullGenderClient.setDateOfBirth(Date.valueOf("1990-11-20"));
//        nullGenderClient.setGender(null);
//
//        //nullDateOfBirthCLient
//        IClientDto nullDateOfBirthClient = new ClientDto();
//        nullDateOfBirthClient.setId(359);
//        nullDateOfBirthClient.setName("testNoDateOfBirhtName");
//        nullDateOfBirthClient.setSecondName("testNoDateOfBirhtSurname");
//        nullDateOfBirthClient.setEmail("testNoDateOfBirhtMail@gmail.com");
//        nullDateOfBirthClient.setDateOfBirth(null);
//        nullDateOfBirthClient.setGender('F');
//
//        //nullEmailClient
//        IClientDto nullEmailClient = new ClientDto();
//        nullEmailClient.setId(360);
//        nullEmailClient.setName("testNoEmailName");
//        nullEmailClient.setSecondName("testNoEmailSurname");
//        nullEmailClient.setEmail(null);
//        nullEmailClient.setDateOfBirth(Date.valueOf("1990-11-20"));
//        nullEmailClient.setGender('F');
//
//        //nullSecondNameClient
//        IClientDto nullSecondNameClient = new ClientDto();
//        nullSecondNameClient.setId(361);
//        nullSecondNameClient.setName("testNoSecondNameName");
//        nullSecondNameClient.setSecondName(null);
//        nullSecondNameClient.setEmail("testNoSecondNameMail@gmail.com");
//        nullSecondNameClient.setDateOfBirth(Date.valueOf("1990-11-20"));
//        nullSecondNameClient.setGender('M');
//
//        //nullNameClient
//        IClientDto nullNameClient = new ClientDto();
//        nullNameClient.setId(362);
//        nullNameClient.setName(null);
//        nullNameClient.setSecondName("testNoNameSurname");
//        nullNameClient.setEmail("testNoNameMail@gmail.com");
//        nullNameClient.setDateOfBirth(Date.valueOf("1990-11-20"));
//        nullNameClient.setGender('N');
//
//        //validClient
//        IClientDto validClient = new ClientDto();
//        validClient.setId(357);
//        validClient.setName("testForReadName");
//        validClient.setSecondName("testForReadSurname");
//        validClient.setEmail("testMail@gmail.com");
//        validClient.setDateOfBirth(Date.valueOf("1990-11-20"));
//        validClient.setGender('M');
//
//        assertEquals(this.countDataBaseRecords,actualClientList.size());
//
//        assertTrue(actualClientList.contains(validClient));
//        assertTrue(actualClientList.contains(nullClient));
//        assertTrue(actualClientList.contains(nullGenderClient));
//        assertTrue(actualClientList.contains(nullDateOfBirthClient));
//        assertTrue(actualClientList.contains(nullEmailClient));
//        assertTrue(actualClientList.contains(nullSecondNameClient));
//        assertTrue(actualClientList.contains(nullNameClient));
//
//
//
//    }

    //update
    @Test
    public void updateValidClient() throws SQLException {
        int id = 200;
        IClientDto clientOld = clientDao.read(id);
        IClientDto clientNew = new ClientDto();
        clientNew.setId(id);
        clientNew.setName("testName");
        clientNew.setSecondName("testSecondName");
        clientNew.setEmail("test@test.com");
        clientNew.setDateOfBirth(Date.valueOf("2000-01-01"));
        clientNew.setGender('M');

        assertNotEquals(clientNew,clientOld);

        boolean result =clientDao.update(clientNew);

        IClientDto clientNewRead = clientDao.read(id);

        assertNotEquals(clientOld,clientNewRead);
        assertEquals(clientNew,clientNewRead);

        assertEquals(clientOld.getId(),clientNew.getId(),clientNewRead.getId());
        assertEquals(id,clientNew.getId(),clientNewRead.getId());
        assertEquals(clientOld.getId(),id,clientNewRead.getId());

        assertTrue(result);


    }

    @Test
    public void updateClientIncorrectEmail() throws SQLException {
        int id = 200;
        IClientDto clientOld = clientDao.read(id);
        IClientDto clientNew = new ClientDto();
        clientNew.setId(id);
        clientNew.setName("testName");
        clientNew.setSecondName("testSecondName");
        clientNew.setEmail("test@f");
        clientNew.setDateOfBirth(Date.valueOf("2000-01-01"));
        clientNew.setGender('M');

        assertNotEquals(clientNew,clientOld);

        assertThrows("incorrect email",InvalidParameterException.class,()-> {
            clientDao.update(clientNew);

        });
    }

    @Test
    public void updateRecordNotExist(){
        IClientDto clientNew = new ClientDto();
        clientNew.setId(9999999);
        clientNew.setName("testName");
        clientNew.setSecondName("testSecondName");
        clientNew.setEmail("test@test.com");
        clientNew.setDateOfBirth(Date.valueOf("2000-01-01"));
        clientNew.setGender('M');
        assertThrows("record does't exist",InvalidParameterException.class,()->{
            clientDao.update(clientNew);
        });
    }

    @Test
    public void updateRecordWithNegativeId(){
        IClientDto clientNew = new ClientDto();
        clientNew.setId(-1);
        clientNew.setName("testName");
        clientNew.setSecondName("testSecondName");
        clientNew.setEmail("test@test.com");
        clientNew.setDateOfBirth(Date.valueOf("2000-01-01"));
        clientNew.setGender('M');
        assertThrows("ID can't be negative",InvalidParameterException.class,() -> {
            clientDao.update(clientNew);


        });
    }

    @Test
    public void updateClientWithZeroId(){
        IClientDto client = new ClientDto();
        client.setId(0);
        client.setName("testName");
        client.setSecondName("testSecondName");
        client.setEmail("test@test.com");
        client.setDateOfBirth(Date.valueOf("1950-05-13"));
        client.setGender('M');
        assertThrows("ID can't be equals '0'", InvalidParameterException.class, () -> {
            clientDao.update(client);
        });
    }

//    @Test
//    public void updateClientNullParameters() throws SQLException {
//        IClientDto clientNull = clientDao.read(356);
//
//        clientNull.setId(200);
//        boolean result = clientDao.update(clientNull);
//        IClientDto clientUpdated = clientDao.read(200);
//
//        assertEquals(clientNull,clientUpdated);
//        assertEquals(200,clientNull.getId(),clientUpdated.getId());
//        assertTrue(result);
//    }

    //delete
    @Test
    public void deleteValidClient() throws SQLException {
        IClientDto readClient = clientDao.read(201);

        assertEquals("testDeleteName",readClient.getName());

        boolean result = clientDao.delete(readClient);
        assertTrue(result);

        assertThrows("record does't exist",InvalidParameterException.class,() -> {
            clientDao.read(201);
        });
    }

    @Test
    public void deleteClientWithNegativeIdTest1(){
        IClientDto validClient = new ClientDto();
        validClient.setId(-1);
        validClient.setName(null);
        validClient.setSecondName("testNoNameSurname");
        validClient.setEmail("testNoNameMail@gmail.com");
        validClient.setDateOfBirth(Date.valueOf("1990-11-20"));
        validClient.setGender('N');

        assertThrows("ID can't be negative",InvalidParameterException.class,() -> {
            clientDao.delete(validClient);
        });


    }

    @Test
    public void deleteClientWithNegativeIdTest2(){

        assertThrows("ID can't be negative",InvalidParameterException.class,() -> {
            clientDao.delete(-1);
        });

    }

    @Test
    public void deleteClientWithNullIdTest1(){
        IClientDto validClient = new ClientDto();
        validClient.setId(null);
        validClient.setName(null);
        validClient.setSecondName("testNoNameSurname");
        validClient.setEmail("testNoNameMail@gmail.com");
        validClient.setDateOfBirth(Date.valueOf("1990-11-20"));
        validClient.setGender('N');

        assertThrows("ID can't be negative",InvalidParameterException.class,() -> {
            clientDao.delete(validClient);
        });

    }

    @Test
    public void deleteClientWithNullIdTest2() {
        Integer i = null;

        assertThrows("ID can't be negative", InvalidParameterException.class, () -> {
            clientDao.delete(i);
        });
    }

//    @Test
//    public void getLastId() throws SQLException {
//        assertEquals(362,clientDao.getLastId());
//    }

    @Test
    public void crudFullCycleWithValidClient() throws SQLException {
        IClientDto validClient = new ClientDto();
        validClient.setId(150);
        validClient.setName("fullCycleTest");
        validClient.setSecondName("fullCycleTestSecondName");
        validClient.setEmail("fullCycleTest@gmail.com");
        validClient.setDateOfBirth(Date.valueOf("1990-11-20"));
        validClient.setGender('N');

        clientDao.create(validClient);

        IClientDto readClient = clientDao.read(150);

        assertEquals(validClient.getId(),readClient.getId(),150);
        assertEquals(validClient,readClient);

        IClientDto validUpdatedClient = new ClientDto();
        validUpdatedClient.setId(150);
        validUpdatedClient.setName("fullCycleTestUpd");
        validUpdatedClient.setSecondName("fullCycleTestUpdSecond");
        validUpdatedClient.setEmail("fullCycleTestUpd@gmail.com");
        validUpdatedClient.setDateOfBirth(Date.valueOf("1991-11-20"));
        validUpdatedClient.setGender('M');

        assertNotEquals(readClient,validUpdatedClient);
        assertEquals(150,readClient.getId(),validUpdatedClient.getId());

        clientDao.update(validUpdatedClient);

        IClientDto updatedReadClient = clientDao.read(150);

        assertEquals(validUpdatedClient,updatedReadClient);
        assertNotEquals(readClient,updatedReadClient);

        clientDao.delete(150);

        assertThrows("can't delete. record does't exist",InvalidParameterException.class,()-> {
            clientDao.read(150);
        });

    }

    @Test
    public void crudFullCycleWithNullClient() throws SQLException {
        IClientDto validClient = new ClientDto();
        validClient.setId(150);
        validClient.setName(null);
        validClient.setSecondName(null);
        validClient.setEmail(null);
        validClient.setDateOfBirth(null);
        validClient.setGender(null);

        clientDao.create(validClient);

        IClientDto readClient = clientDao.read(150);

        assertEquals(validClient.getId(),readClient.getId(),150);
        assertEquals(validClient,readClient);

        IClientDto validUpdatedClient = new ClientDto();
        validUpdatedClient.setId(150);
        validUpdatedClient.setName("fullCycleTestUpd");
        validUpdatedClient.setSecondName("fullCycleTestUpdSecond");
        validUpdatedClient.setEmail("fullCycleTestUpd@gmail.com");
        validUpdatedClient.setDateOfBirth(Date.valueOf("1991-11-20"));
        validUpdatedClient.setGender('M');

        assertNotEquals(readClient,validUpdatedClient);
        assertEquals(150,readClient.getId(),validUpdatedClient.getId());

        clientDao.update(validUpdatedClient);

        IClientDto updatedReadClient = clientDao.read(150);

        assertEquals(validUpdatedClient,updatedReadClient);
        assertNotEquals(readClient,updatedReadClient);

        clientDao.delete(150);

        assertThrows("can't delete. record does't exist",InvalidParameterException.class,()-> {
            clientDao.read(150);
        });
    }





}