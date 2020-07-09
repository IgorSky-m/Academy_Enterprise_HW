package by.academy.it;

import by.academy.it.mysql.ClientDaoImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main2 {
    private static final Logger logger = Logger.getLogger(Main2.class.getName());

    public static void main(String[] args) {
        try {
            IClientDao clientDao = ClientDaoFactory.getClientDao("mysql");
            int id = clientDao.getLastId();
            id ++;
            System.out.println(id);
            ClientDto client = new ClientDto();
            client.setId(id);
            client.setName("Igro");
            client.setSecondName("Skachko");
            client.setEmail("scarydisco@gmail.com");
            client.setDateOfBirth(Date.valueOf("1991-04-17"));
            client.setGender('m');
            logger.log(Level.INFO,"Created: "+client.getName());
            clientDao.create(client);
            ClientDto clientRead = clientDao.read(id);
            logger.log(Level.INFO,"Readed: "+clientRead.getName());
            clientRead.setName("vasiliypup");
            clientRead.setGender('F');
            clientDao.update(clientRead);
            ClientDto clientUpdate = clientDao.read(id);
            logger.log(Level.INFO,"Updated: "+clientUpdate.getName());
            clientDao.delete(id);
            logger.log(Level.INFO,"Deleted: "+clientDao.read(id).getName());

        } catch (SQLException throwables) {
            logger.log(Level.SEVERE,throwables.getMessage(),throwables);
            System.exit(-1);
        }finally {
            logger.info("Finished successfully");
            System.exit(0);
        }
    }

}
