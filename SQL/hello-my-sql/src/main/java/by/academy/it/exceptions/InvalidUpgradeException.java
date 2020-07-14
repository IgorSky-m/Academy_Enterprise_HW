package by.academy.it.exceptions;

public class InvalidUpgradeException extends ClientDaoException {
    public InvalidUpgradeException(String reason) {
        super(reason);
    }

    public InvalidUpgradeException() {
        super();
    }
}
