package com.booking.Common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()   // Cho phép truy cập swagger không cần login
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login.disable())  // ❌ Tắt form login
                .httpBasic(basic -> basic.disable()); // ❌ Tắt Basic Auth

        return http.build();
    }
}
