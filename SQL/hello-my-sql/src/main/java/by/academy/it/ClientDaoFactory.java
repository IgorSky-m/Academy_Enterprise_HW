package by.academy.it;

import by.academy.it.interfaces.IClientDao;
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
        } else if ("mysql_test".equals(dataBase)) {
            if (clientDao == null) clientDao = new ClientDaoImpl(true);
            return clientDao;
        }
        throw new InvalidParameterException("no such database implemented");
    }

}
