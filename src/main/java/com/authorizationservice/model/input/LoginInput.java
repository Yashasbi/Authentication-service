package com.authorizationservice.model.input;


import lombok.Data;

@Data
public class LoginInput {

   private String emailId;

   private String password;
}
