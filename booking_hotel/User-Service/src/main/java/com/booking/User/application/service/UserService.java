package com.booking.User.application.service;

import com.booking.User.adapters.dto.UserDTO;
import com.booking.User.application.mapper.UserMapper;
import com.booking.User.domain.model.User;
import com.booking.User.infrastructure.persistence.entity.UserEntity;
import com.booking.User.infrastructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Admin CRUD
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(UUID id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDTO createUser(UserDTO dto) {
        UserEntity entity = UserMapper.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return UserMapper.toDTO(userRepository.save(entity));
    }

    public UserDTO updateUser(UUID id, UserDTO dto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFullname(dto.getFullname());
        user.setPhone(dto.getPhone());
        user.setRoles(dto.getRoles());
        if (dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return UserMapper.toDTO(userRepository.save(user));
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    // Customer self-management
    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDTO updateUserByEmail(String email, UserDTO dto) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFullname(dto.getFullname());
        user.setPhone(dto.getPhone());
        if (dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return UserMapper.toDTO(userRepository.save(user));
    }

    public void deleteUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
