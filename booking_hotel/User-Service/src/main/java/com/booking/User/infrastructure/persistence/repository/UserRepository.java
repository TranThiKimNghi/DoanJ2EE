package com.booking.User.infrastructure.persistence.repository;

import com.booking.User.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByRoles(String role);
    List<UserEntity> findByFullnameContainingIgnoreCase(String fullname);
}
