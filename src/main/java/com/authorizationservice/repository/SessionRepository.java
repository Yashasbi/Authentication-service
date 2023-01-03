package com.authorizationservice.repository;

import com.authorizationservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    @Query(value = "select * from session where session.user_id = ?1", nativeQuery = true)
    Session findSessionByUser(String userId);

}
