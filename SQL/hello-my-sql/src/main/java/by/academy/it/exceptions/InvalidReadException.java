package by.academy.it.exceptions;

public class InvalidReadException extends ClientDaoException {
    public InvalidReadException(String reason) {
        super(reason);
    }

    public InvalidReadException() {
        super();
    }
}
