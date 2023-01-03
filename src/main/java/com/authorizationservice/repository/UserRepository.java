package com.authorizationservice.repository;

import com.authorizationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "select * from user where user.email_id = ?1", nativeQuery = true)
    User findUserByEmail(String emailId);

}
