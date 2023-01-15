package com.authorizationservice.repository;

import com.authorizationservice.model.entities.Session;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SessionRepository {

//    @Query(value = "select * from session where session.user_id = ?1", nativeQuery = true)
//    Session findSessionByUser(String userId);

    @Autowired
    private MemcachedClient memcachedClient;

    public void saveSession(Session session) {
        memcachedClient.add(session.getSessionId().toString(), 300, session);
    }

    public Session getSession(UUID sessionId) {
        return (Session) memcachedClient.get(sessionId.toString());
    }

}
