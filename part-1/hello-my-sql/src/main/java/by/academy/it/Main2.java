package by.academy.it;

import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main2 {
    private static final Logger logger = Logger.getLogger(Main2.class.getName());

    public static void main(String[] args) {
        try {
            IClientDao clientDto = ClientDaoFactory.getClientDao("mysql");
            ClientDto client = new ClientDto();
            client.setId(3);
            client.setName("Игорь");
            client.setSecondName("Skachko");
            client.setEmail("scarydisco@gmail.com");
            client.setDateOfBirth(Date.valueOf("1991-04-17"));
            client.setGender('m');


            clientDto.create(client);
        } catch (SQLException throwables) {
            logger.log(Level.SEVERE,throwables.getMessage(),throwables);
            System.exit(-1);
        }finally {
            logger.info("Finished successfully");
            System.exit(0);
        }
    }

}
