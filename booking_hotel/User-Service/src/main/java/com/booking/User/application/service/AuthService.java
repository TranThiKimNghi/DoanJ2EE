package com.booking.User.application.service;

import com.booking.User.adapters.dto.AuthResponse;
import com.booking.User.adapters.dto.LoginRequest;
import com.booking.User.adapters.dto.RegisterRequest;
import com.booking.User.infrastructure.persistence.entity.UserEntity;
import com.booking.User.infrastructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    // Đăng ký
    public AuthResponse register(RegisterRequest req) {
        String email = req.getEmail().trim().toLowerCase();

        if (repo.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }

        UserEntity user = UserEntity.builder()
                .fullname(req.getFullname())
                .email(email)
                .phone(req.getPhone())
                .roles("customer") // mặc định customer
                .password(encoder.encode(req.getPassword()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .deleted(false)
                .build();

        repo.save(user);

        // Phát hành token có userId
        String token = jwt.generate(user.getId().toString(), user.getEmail(), user.getRoles());
        return new AuthResponse(token, user.getRoles());
    }

    // Đăng nhập
    public AuthResponse login(LoginRequest req) {
        UserEntity user = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Email hoặc mật khẩu không đúng"));

        if(!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Email hoặc mật khẩu không đúng");
        }

        // Phát hành token có userId
        String token = jwt.generate(user.getId().toString(), user.getEmail(), user.getRoles());
        return new AuthResponse(token, user.getRoles());
    }
}
