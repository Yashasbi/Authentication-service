package com.authorizationservice.service;

import com.authorizationservice.exception.InvalidUserException;
import com.authorizationservice.exception.UserAlreadyLoggedInException;
import com.authorizationservice.model.Session;
import com.authorizationservice.model.User;
import com.authorizationservice.model.input.LoginInput;
import com.authorizationservice.model.input.UserInput;
import com.authorizationservice.repository.UserRepository;
import com.authorizationservice.utils.ApiKeyValidator;
import com.authorizationservice.utils.PasswordValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ApiKeyValidator apiKeyValidator;

    public void signUpUser(UserInput userInput) {

        User user = User.builder()
                .name(userInput.getName())
                .password(userInput.getPassword())
                .emailId(userInput.getEmailId()).build();

        userRepository.save(user);
    }

    public UUID loginUser(LoginInput loginInput) {

        User user = null;

        user = userRepository.findUserByEmail(loginInput.getEmailId());

        if(user == null) {
            throw new InvalidUserException("The given user does not exists");
        }
        passwordValidator.validatePassword(loginInput.getPassword(), user.getPassword());

        Session savedSession = sessionService.findSessionByUser(user.getUserId().toString());
        log.info("Saved session is " + savedSession);

        if(savedSession != null) {
            throw new UserAlreadyLoggedInException("The given user is already logged in");
        }

        Session session = Session
                            .builder()
                            .userId(user.getUserId())
                            .build();

        return sessionService.createSession(session);

    }



    public User getUserById(UUID userId) {

        log.info("Getting user by id {}", userId);
        return userRepository.getReferenceById(userId);
    }
}
