package by.academy.it.exceptions;

public class InvalidDeleteException extends ClientDaoException {
    public InvalidDeleteException(String reason) {
        super(reason);
    }

    public InvalidDeleteException() {
        super();
    }
}
