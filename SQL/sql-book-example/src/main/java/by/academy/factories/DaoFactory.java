package by.academy.factories;

import by.academy.dao.ExpensesDao;
import by.academy.dao.ReceiversDao;
import by.academy.interfaces.IDao;

import java.security.InvalidParameterException;
import java.sql.SQLException;

public class DaoFactory {
    private static IDao iDao;
    public static IDao getDao(String daoName) throws SQLException {

        switch (daoName) {
            case "expenses" : {
                if (iDao == null) iDao = new ExpensesDao();
                break;
            }
            case "expenses_test": {
                if (iDao == null)  iDao = new ExpensesDao(true);
                break;
            }
            case "receivers" : {
                if (iDao == null)  iDao = new ReceiversDao();
                break;
            }
            case "receivers_test": {
                if (iDao == null)  iDao = new ReceiversDao(true);
                break;
            }
            default: throw new InvalidParameterException("no surch database");
        }


        return iDao;
    }

}
