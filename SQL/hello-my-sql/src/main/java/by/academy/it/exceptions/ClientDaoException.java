package by.academy.it.exceptions;

import java.security.InvalidParameterException;

public class ClientDaoException extends InvalidParameterException {

    public ClientDaoException(String reason) {
        super(reason);
    }

    public ClientDaoException() {
        super();
    }

}
