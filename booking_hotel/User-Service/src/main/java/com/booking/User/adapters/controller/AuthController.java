package com.booking.User.adapters.controller;

import com.booking.User.adapters.dto.AuthResponse;
import com.booking.User.adapters.dto.LoginRequest;
import com.booking.User.adapters.dto.RegisterRequest;
import com.booking.User.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService auth;
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest req) {
        return auth.register(req);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req) {
        return auth.login(req);
    }

}
