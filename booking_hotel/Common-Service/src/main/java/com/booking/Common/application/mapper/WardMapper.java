package com.booking.Common.application.mapper;

import com.booking.Common.adapters.dto.Reponse.WardResponseDTO;
import com.booking.Common.adapters.dto.Request.WardRequestDTO;
import com.booking.Common.domain.model.Ward;
import com.booking.Common.infrastructure.persistence.entity.WardEntity;

import java.time.LocalDateTime;

public class WardMapper {
    public static Ward toDomain(WardEntity entity) {
        if (entity == null) return null;

        Ward domain = new Ward();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setProvinceId(entity.getProvinceId());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setCreatedBy(entity.getCreatedBy());
        domain.setUpdatedAt(entity.getUpdatedAt());
        domain.setUpdatedBy(entity.getUpdatedBy());
        domain.setDeleted(entity.getIsDeleted());

        return domain;
    }

    // DOMAIN ➜ ENTITY
    public static WardEntity toEntity(Ward domain) {
        if (domain == null) return null;

        WardEntity entity = new WardEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setProvinceId(domain.getProvinceId());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setCreatedBy(domain.getCreatedBy());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setUpdatedBy(domain.getUpdatedBy());
        entity.setIsDeleted(domain.isDeleted());

        return entity;
    }

    // DOMAIN ➜ RESPONSE DTO
    public static WardResponseDTO toResponseDTO(Ward domain) {
        if (domain == null) return null;

        WardResponseDTO dto = new WardResponseDTO();
        dto.setId(domain.getId());
        dto.setName(domain.getName());
        dto.setProvinceId(domain.getProvinceId());

        return dto;
    }

    // REQUEST DTO ➜ DOMAIN
    public static Ward fromRequestDTO(WardRequestDTO dto) {
        if (dto == null) return null;

        Ward domain = new Ward();
        domain.setName(dto.getName());
        domain.setProvinceId(dto.getProvinceId());

        domain.setCreatedAt(LocalDateTime.now());
        domain.setUpdatedAt(LocalDateTime.now());
        domain.setDeleted(false);

        return domain;
    }
}
