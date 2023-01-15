package com.authorizationservice.repository;

import com.authorizationservice.model.entities.Session;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SessionRepository {

    @Autowired
    private MemcachedClient memcachedClient;

    public void saveSession(Session session) {
        memcachedClient.add(session.getSessionId().toString(), 300, session);
    }

    public Session getSession(UUID sessionId) {
        return (Session) memcachedClient.get(sessionId.toString());
    }

}
