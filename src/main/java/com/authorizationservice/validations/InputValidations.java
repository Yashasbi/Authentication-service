package com.authorizationservice.validations;

import com.authorizationservice.constants.Constants;
import org.springframework.stereotype.Component;

@Component
public class InputValidations {

    public boolean validateEmailId(final String emailId) {
        return emailId.contains("@");
    }

    public boolean validatePassword(final String inputPassword) {
        return inputPassword.length() == Constants.INPUT_PASSWORD_LENGTH;
    }
}
