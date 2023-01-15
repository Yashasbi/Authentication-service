package com.authorizationservice.enums;

import lombok.Getter;

public enum CustomHttpCodes {

    VERY_GOOD(600, "Very God Input");


    @Getter
    private final int value;
    @Getter
    private final String reasonPhrase;

    private CustomHttpCodes(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }
}
