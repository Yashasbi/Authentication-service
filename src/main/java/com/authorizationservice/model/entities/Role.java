package com.authorizationservice.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NonNull
    @Column(unique = true, length = 30)
    private String roleName;

    @NonNull
    private String roleDescription;

}
