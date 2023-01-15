package com.authorizationservice.model.entities;


import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@ToString
@NoArgsConstructor

public class Session implements Serializable {

    private UUID sessionId;

    private UUID userId;

    private Date createdAt;
}
