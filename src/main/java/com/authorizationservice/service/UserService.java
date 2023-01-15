package com.authorizationservice.service;

import com.authorizationservice.encryption.TextEncryptor;
import com.authorizationservice.exception.InvalidUserException;
import com.authorizationservice.exception.UserAlreadyLoggedInException;
import com.authorizationservice.model.entities.Role;
import com.authorizationservice.model.entities.Session;
import com.authorizationservice.model.entities.User;
import com.authorizationservice.model.input.LoginInput;
import com.authorizationservice.model.input.UserInput;
import com.authorizationservice.repository.UserRepository;
import com.authorizationservice.utils.ApiKeyValidator;
import com.authorizationservice.utils.PasswordValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.authorizationservice.constants.Constants.ADMIN_ROLE_ID;
import static com.authorizationservice.constants.Constants.DEFAULT_ROLE_ID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ApiKeyValidator apiKeyValidator;

    @Autowired
    private TextEncryptor textEncryptor;

    public void signUpUser(UserInput userInput) {

        String encryptedPassword = textEncryptor.encrypt(userInput.getPassword());

        //Get default role
        Role defaultRole = roleService.getRole(DEFAULT_ROLE_ID);
        Role adminRole = roleService.getRole(ADMIN_ROLE_ID);

        User user = User.builder()
                .name(userInput.getName())
                .password(encryptedPassword)
                .emailId(userInput.getEmailId())
                .roles(List.of(defaultRole, adminRole))
                .build();

        userRepository.save(user);
    }

    public UUID loginUser(LoginInput loginInput) {

        User user = null;

        user = userRepository.findUserByEmail(loginInput.getEmailId());

        if(user == null) {
            throw new InvalidUserException("The given user does not exists");
        }

        passwordValidator.validatePassword(loginInput.getPassword(), user.getPassword()   );

//        Session savedSession = sessionService.findSessionByUser(user.getUserId().toString());
//        log.info("Saved session is " + savedSession);
//
//        if(savedSession != null) {
//            throw new UserAlreadyLoggedInException("The given user is already logged in");
//        }

        return sessionService.createSession(user.getUserId());

    }



    public User getUserById(UUID userId) {

        log.info("Getting user by id {}", userId);
        return userRepository.findById(userId).get();
//                getReferenceById(userId);
    }
}
