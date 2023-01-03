package com.authorizationservice.exception;

public class InvalidSessionIdException extends RuntimeException {

    public InvalidSessionIdException(String s) {
        super(s);
    }

    public InvalidSessionIdException(String s, Throwable t) {
        super(s, t);
    }
}
