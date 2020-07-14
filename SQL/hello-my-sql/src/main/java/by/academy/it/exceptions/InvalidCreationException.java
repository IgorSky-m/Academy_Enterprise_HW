package by.academy.it.exceptions;




public class InvalidCreationException extends ClientDaoException {

    public InvalidCreationException(String reason) {
        super(reason);
    }

    public InvalidCreationException() {
        super();
    }
}