package com.booking.User.adapters.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String fullname;
    private String email;
    private String phone;
    private String password;
}
