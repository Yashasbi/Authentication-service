package com.authorizationservice.encryption;

public interface TextEncryptor {

    public String encrypt(String text);
    public boolean verify(String text, String encryptedText);
}
