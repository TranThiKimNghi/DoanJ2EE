package com.booking.User.domain.model;

import lombok.Setter;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class User {
    private UUID id;
    private String fullname ;
    private String email;
    private String phone;
    private String roles; // admin / customer
    private String password;
    private LocalDateTime createdAt;
    private UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;
    private boolean isDeleted;
}
