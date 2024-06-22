package com.prueba.nisum.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
public class UserResponse {

    private UUID id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
}
