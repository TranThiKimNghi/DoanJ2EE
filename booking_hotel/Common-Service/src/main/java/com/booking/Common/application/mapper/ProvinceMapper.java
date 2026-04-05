package com.booking.Common.application.mapper;

import com.booking.Common.adapters.dto.Reponse.ProvinceResponseDTO;
import com.booking.Common.adapters.dto.Request.ProvinceRequestDTO;
import com.booking.Common.domain.model.Province;
import com.booking.Common.infrastructure.persistence.entity.ProvinceEntity;

public class ProvinceMapper {
    public static Province toDomain(ProvinceRequestDTO dto) {
        Province domain = new Province();
        domain.setName(dto.getName());
        return domain;
    }

    // Entity → Domain
    public static Province toDomain(ProvinceEntity entity) {
        if (entity == null) return null;

        Province domain = new Province();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setCreatedBy(entity.getCreatedBy());
        domain.setUpdatedAt(entity.getUpdatedAt());
        domain.setUpdatedBy(entity.getUpdatedBy());
        domain.setDeleted(entity.getIsDeleted());
        return domain;
    }

    // Domain → Entity
    public static ProvinceEntity toEntity(Province domain) {
        ProvinceEntity entity = new ProvinceEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setCreatedBy(domain.getCreatedBy());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setUpdatedBy(domain.getUpdatedBy());
        entity.setIsDeleted(domain.isDeleted());
        return entity;
    }

    // Domain → ResponseDTO
    public static ProvinceResponseDTO toResponseDTO(Province domain) {
        return ProvinceResponseDTO.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();
    }
}
