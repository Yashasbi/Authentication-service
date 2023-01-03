package com.authorizationservice.model.output;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthenticatedUser {

    private UUID userId;

    private UUID sessionId;

    private String errorMessage;
}
