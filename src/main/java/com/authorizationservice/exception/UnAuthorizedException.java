package com.authorizationservice.exception;

public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException(String s) {
        super(s);
    }

    public UnAuthorizedException(String s, Throwable t) {
        super(s, t);
    }
}
