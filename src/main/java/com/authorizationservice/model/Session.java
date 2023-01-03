package com.authorizationservice.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString

@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(value = Types.VARCHAR)
    private UUID sessionId;

    @JdbcTypeCode(value = Types.VARCHAR)
    private UUID userId;

    private Date createdAt;


    @PrePersist
    private void onCreate() {
        createdAt = new Date();
    }
}
