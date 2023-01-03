package com.authorizationservice.exception;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String s) {
        super(s);
    }
    public InvalidCredentialsException(String s, Throwable t) {
        super(s, t);
    }
}
