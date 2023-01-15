package com.authorizationservice.encryption;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.authorizationservice.constants.Constants;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class BCryptEncryptor implements TextEncryptor {

    @NonNull
    private final BCrypt.Hasher hasher;

    @NonNull
    private final BCrypt.Verifyer verifyer;

    BCryptEncryptor() {
        hasher = BCrypt.withDefaults();
        verifyer = BCrypt.verifyer();
    }



    @Override
    public String encrypt(String text) {
        return hasher.hashToString(Constants.ENCRYPTION_COST, text.toCharArray());
    }

    @Override
    public boolean verify(String text, String encryptedText) {
        BCrypt.Result result = verifyer.verify(text.toCharArray(), encryptedText.toCharArray());
        return result.verified;
    }
}
