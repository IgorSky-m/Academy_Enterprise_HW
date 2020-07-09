package by.academy.it;

import by.academy.it.mysql.ClientDaoImpl;

import java.security.InvalidParameterException;
import java.sql.SQLException;

public class ClientDaoFactory {

    private ClientDaoFactory(){}

    private static ClientDaoImpl clientDao;

    public static IClientDao getClientDao(String dataBase) throws SQLException {
        if ("mysql".equals(dataBase)) {
            if (clientDao == null) clientDao = new ClientDaoImpl();
            return clientDao;
        }
        throw new InvalidParameterException("no such database implemented");
    }

}
