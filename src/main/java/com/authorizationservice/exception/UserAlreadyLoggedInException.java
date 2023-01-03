package com.authorizationservice.exception;

public class UserAlreadyLoggedInException extends RuntimeException {

    public UserAlreadyLoggedInException(String s) {
        super(s);
    }
}
