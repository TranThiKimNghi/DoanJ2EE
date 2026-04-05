package com.booking.User.adapters.controller;

import com.booking.User.adapters.dto.UserDTO;
import com.booking.User.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final UserService userService;

    @GetMapping
    public UserDTO getProfile(Authentication authentication) {
        return userService.getUserByEmail(authentication.getName());
    }

    @PutMapping
    public UserDTO updateProfile(Authentication authentication, @RequestBody UserDTO dto) {
        return userService.updateUserByEmail(authentication.getName(), dto);
    }

    @DeleteMapping
    public void deleteProfile(Authentication authentication) {
        userService.deleteUserByEmail(authentication.getName());
    }
}
