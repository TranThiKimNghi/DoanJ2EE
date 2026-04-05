package com.booking.Booking.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private byte[] secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = secret.getBytes(StandardCharsets.UTF_8);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }

    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public String getRoleFromToken(String token) {
        return getClaims(token).get("role", String.class);
    }

    public UUID getUserIdFromToken(String token) {
        String userId = getClaims(token).get("userId", String.class);

        if (userId == null) {
            throw new RuntimeException("JWT token không chứa userId");
        }

        return UUID.fromString(userId);
    }
}
