package com.authorizationservice.controller;

import com.authorizationservice.exception.*;
import com.authorizationservice.model.input.LoginInput;
import com.authorizationservice.model.input.UserInput;
import com.authorizationservice.model.output.AuthenticatedUser;
import com.authorizationservice.service.SessionService;
import com.authorizationservice.service.UserService;
import com.authorizationservice.validations.InputValidations;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private InputValidations inputValidations;

    @PostMapping("/v1/user/signup")
    public ResponseEntity<String> signUpUser(@RequestBody UserInput user) {

        if(!inputValidations.validateEmailId(user.getEmailId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Enter a valid emailId");
        }
        if(!inputValidations.validatePassword(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Enter a valid password");
        }

        try {
            userService.signUpUser(user);
        }
        catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/v1/user/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginInput loginInput) {

        UUID sessionId = null;
        final HttpHeaders httpHeaders = new HttpHeaders();

        try {
            sessionId = userService.loginUser(loginInput);
        }
        catch(InvalidCredentialsException invalidCredentialsException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(invalidCredentialsException.getMessage());
        }
        catch(InvalidUserException invalidUserException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(invalidUserException.getMessage());
        }
        catch(UserAlreadyLoggedInException userAlreadyLoggedInException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userAlreadyLoggedInException.getMessage());
        }

        httpHeaders.set("session-id", sessionId.toString());
        return ResponseEntity.ok().headers(httpHeaders).build();
    }

    @PostMapping("/v1/user/logout")
    public ResponseEntity<String> logoutUser(@NonNull @RequestHeader(value = "session-id") UUID sessionId) {
        try {
            sessionService.deleteSession(sessionId);
        }
        catch(InvalidSessionIdException invalidSessionIdException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Session-id invalid");
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/session/details")
    public ResponseEntity<AuthenticatedUser> fetchSessionDetails(@NonNull @RequestHeader(value = "session-id") UUID sessionId, @NonNull @RequestHeader
            (value = "api-key") String apiKey) {

        ResponseEntity<AuthenticatedUser> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(sessionService.fetchSessionDetails(sessionId, apiKey));
        }
        catch(UnAuthorizedException unAuthorizedException) {
           return ResponseEntity.status(HttpStatus.FORBIDDEN).body(AuthenticatedUser.builder()
                           .errorMessage("User unauthorized").build());
        }
        catch(InvalidSessionIdException invalidSessionIdException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AuthenticatedUser.builder()
                                        .errorMessage("Invalid session id").build());
        }
        return responseEntity;
    }
}
