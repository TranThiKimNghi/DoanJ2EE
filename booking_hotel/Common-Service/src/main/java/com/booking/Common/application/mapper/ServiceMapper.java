package com.booking.Common.application.mapper;

import com.booking.Common.adapters.dto.Reponse.ServiceResponseDTO;
import com.booking.Common.adapters.dto.Request.ServiceRequestDTO;
import com.booking.Common.domain.model.Service;
import com.booking.Common.infrastructure.persistence.entity.ServiceEntity;

public class ServiceMapper {
    // RequestDTO -> Domain
    public static Service toDomain(ServiceRequestDTO dto) {
        Service domain = new Service();
        domain.setName(dto.getName());
        domain.setPrice(dto.getPrice());
        return domain;
    }

    // Entity -> Domain
    public static Service toDomain(ServiceEntity entity) {
        Service domain = new Service();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setPrice(entity.getPrice());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setCreatedBy(entity.getCreatedBy());
        domain.setUpdatedAt(entity.getUpdatedAt());
        domain.setUpdatedBy(entity.getUpdatedBy());
        domain.setDeleted(entity.getIsDeleted());
        return domain;
    }

    // Domain → Entity
    public static ServiceEntity toEntity(Service domain) {
        ServiceEntity entity = new ServiceEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setPrice(domain.getPrice());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setCreatedBy(domain.getCreatedBy());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setUpdatedBy(domain.getUpdatedBy());
        entity.setIsDeleted(domain.isDeleted());
        return entity;
    }

    // Domain → ResponseDTO
    public static ServiceResponseDTO toResponseDTO(Service domain) {
        return ServiceResponseDTO.builder()
                .id(domain.getId())
                .name(domain.getName())
                .price(domain.getPrice())
                .build();
    }
}
