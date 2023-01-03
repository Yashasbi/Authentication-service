package com.authorizationservice.utils;

import com.authorizationservice.exception.InvalidCredentialsException;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {

    public void validatePassword(@NonNull String inputPassword, @NonNull String savedPassword) {
        if(!inputPassword.equals(savedPassword)) {
            throw new InvalidCredentialsException("Password mismatched");
        }
    }
}
