package it.academy.old.exceptions;

import java.security.InvalidParameterException;

public class BlockChainException extends InvalidParameterException {
    public BlockChainException() {
        super();
    }

    public BlockChainException(String msg) {
        super(msg);
    }
}
