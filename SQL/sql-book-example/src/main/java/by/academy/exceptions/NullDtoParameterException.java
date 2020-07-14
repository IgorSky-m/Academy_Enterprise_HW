package by.academy.exceptions;

import java.security.InvalidParameterException;

public class NullDtoParameterException extends InvalidParameterException {
    private static final String DEFAULT_EXCEPTION_MSG = "can't be null";
    public NullDtoParameterException() {
        super(DEFAULT_EXCEPTION_MSG);
    }

    public NullDtoParameterException(String msg) {
        super(msg);
    }
}
