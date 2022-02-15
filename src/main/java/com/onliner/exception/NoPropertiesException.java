package com.onliner.exception;

public class NoPropertiesException extends RuntimeException {

    public NoPropertiesException() {
    }

    public NoPropertiesException(String message) {
        super(message);
    }
}
