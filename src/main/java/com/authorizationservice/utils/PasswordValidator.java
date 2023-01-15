package com.authorizationservice.utils;

import com.authorizationservice.encryption.TextEncryptor;
import com.authorizationservice.exception.InvalidCredentialsException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {

    @Autowired
    TextEncryptor textEncryptor;

    public void validatePassword(@NonNull String inputPassword, @NonNull String encryptedPassword) {
        if(!textEncryptor.verify(inputPassword, encryptedPassword)) {
            throw new InvalidCredentialsException("Password mismatched");
        }
    }
}
