package com.booking.User.adapters.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class UserDTO {
    private UUID id;
    private String fullname;
    private String email;
    private String phone;
    private String password;
    private String roles;

}
