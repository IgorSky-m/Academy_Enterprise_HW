package by.academy.it;



import by.academy.it.interfaces.IClientDao;
import by.academy.it.interfaces.IClientDto;
import by.academy.it.mysql.MySqlDataSource;

import java.security.InvalidParameterException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            IClientDao clientDao = ClientDaoFactory.getClientDao("mysql");
            int id = clientDao.getLastId();
            id ++;
            IClientDto client = new ClientDto();
            client.setId(id);
            client.setName("Igor");
            client.setSecondName("Skachko");
            client.setEmail("scarydisco@gmail.com");
            client.setDateOfBirth(Date.valueOf("1991-04-17"));
            client.setGender('M');
            logger.log(Level.INFO,"Created: "+client.getName());
            //create
                clientDao.create(client);

            //read


            IClientDto clientRead = clientDao.read(id);
            logger.log(Level.INFO,"Readed: "+clientRead.getName());
            //update
            clientRead.setName("Vasily");
            clientRead.setGender('F');
            clientDao.update(clientRead);
            IClientDto clientUpdate = clientDao.read(id);
            logger.log(Level.INFO,"Updated: "+clientUpdate.getName());
            //delete
            clientDao.delete(id);
            logger.log(Level.INFO,"Deleted: "+clientDao.read(id).getName());

        } catch (Exception e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
            System.exit(-1);
        }finally {
            logger.info("Finished successfully");
            System.exit(0);
        }
    }

}
