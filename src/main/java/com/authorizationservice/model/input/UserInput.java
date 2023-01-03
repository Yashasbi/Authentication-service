package com.authorizationservice.model.input;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserInput {

    @NonNull
    private String name;

    @NonNull
    private String emailId;

    @NonNull
    private String password;

}
