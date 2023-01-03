package com.authorizationservice.service;

import com.authorizationservice.exception.InvalidSessionIdException;
import com.authorizationservice.exception.UnAuthorizedException;
import com.authorizationservice.model.Session;
import com.authorizationservice.model.output.AuthenticatedUser;
import com.authorizationservice.repository.SessionRepository;
import com.authorizationservice.utils.ApiKeyValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    ApiKeyValidator apiKeyValidator;

    public UUID createSession(Session inputSession) {

        Session createdSession = sessionRepository.save(inputSession);
        return createdSession.getSessionId();
    }


    public void deleteSession(UUID sessionId) {
        log.info("Deleting session with id {}", sessionId);
        try {
            sessionRepository.deleteById(sessionId);
        }
        catch(EmptyResultDataAccessException entityNotFoundException) {
            throw new InvalidSessionIdException("The given sessionId " +sessionId + " does not exists in db");
        }
    }

    public Session getSessionById(UUID sessionId) {
        log.info("Getting session for sessionId {}", sessionId);
        return sessionRepository.getReferenceById(sessionId);
    }


    public AuthenticatedUser fetchSessionDetails(UUID sessionId, String apiKey) {

        log.info("Validate Api key for sessionId " +sessionId);

        if(apiKeyValidator.validateApiKey(apiKey)) {

            log.info("Validated API key for session-id " +sessionId);

            try {
                Session session = sessionRepository.getReferenceById(sessionId);
                return AuthenticatedUser.builder()
                        .userId(session.getUserId())
                        .sessionId(session.getSessionId())
                        .build();
            }
            catch(EntityNotFoundException e) {
                log.error("The given entity is not found {} " , sessionId);
                throw new InvalidSessionIdException("Given sessionId is invalid");
            }
        }
        else {
            throw new UnAuthorizedException("Invalid API key");
        }
    }

    public Session findSessionByUser(final String userId) {
          return sessionRepository.findSessionByUser(userId);
    }
}
