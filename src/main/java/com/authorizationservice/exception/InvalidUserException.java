package com.authorizationservice.exception;

public class InvalidUserException extends RuntimeException {

    public InvalidUserException(String s) {
        super(s);
    }

    public InvalidUserException(String s, Throwable t) {
        super(s, t);
    }
}
