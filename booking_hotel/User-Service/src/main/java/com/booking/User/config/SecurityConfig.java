package com.booking.User.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                // Tắt CSRF vì dùng token JWT
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                // Stateless: không dùng session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Quy định quyền truy cập
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Cho phép truy cập Swagger và auth mà không cần token
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/api/auth/**"
                        ).permitAll()

                        // API admin chỉ admin
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // API customer chỉ customer
                        .requestMatchers("/api/customer/**").hasRole("CUSTOMER")

                        // Các request /api/** khác đều cần xác thực
                        .requestMatchers("/api/**").authenticated()
                );

        // Thêm JWT filter trước filter mặc định của Spring Security
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}