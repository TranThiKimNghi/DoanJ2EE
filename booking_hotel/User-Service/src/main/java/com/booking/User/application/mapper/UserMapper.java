package com.booking.User.application.mapper;

import com.booking.User.adapters.dto.UserDTO;
import com.booking.User.infrastructure.persistence.entity.UserEntity;

public class UserMapper {
    public static UserDTO toDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setFullname(entity.getFullname());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setRoles(entity.getRoles());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    public static UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setFullname(dto.getFullname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setRoles(dto.getRoles());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}
