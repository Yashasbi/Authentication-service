package com.authorizationservice.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(value = Types.VARCHAR)
    private UUID userId;

    @NonNull
    private String name;

    @Column(unique=true, length = 30)
    @NonNull
    private String emailId;

    @NonNull
    private String password;

    private Date createdAt;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roles;

    @PrePersist
    void intialize() {
        createdAt = new Date();
    }

}
