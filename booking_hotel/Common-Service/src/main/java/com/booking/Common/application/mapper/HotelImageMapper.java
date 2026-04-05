package com.booking.Common.application.mapper;

import com.booking.Common.adapters.dto.Reponse.HotelImageResponseDTO;
import com.booking.Common.adapters.dto.Request.HotelImageRequestDTO;
import com.booking.Common.domain.model.HotelImage;
import com.booking.Common.infrastructure.persistence.entity.HotelImageEntity;

public class HotelImageMapper {
    public static HotelImage toDomain(HotelImageEntity entity) {
        if (entity == null) return null;

        HotelImage domain = new HotelImage();
        domain.setId(entity.getId());
        domain.setHotelId(entity.getHotelId());
        domain.setUrl(entity.getUrl());
        domain.setPrimary(entity.getIsPrimary());
        return domain;
    }

    public static HotelImageEntity toEntity(HotelImage domain) {
        HotelImageEntity entity = new HotelImageEntity();
        entity.setId(domain.getId());
        entity.setHotelId(domain.getHotelId());
        entity.setUrl(domain.getUrl());
        entity.setIsPrimary(domain.isPrimary());
        return entity;
    }

    public static HotelImage toDomain(HotelImageRequestDTO dto) {
        if (dto == null) return null;

        HotelImage domain = new HotelImage();
        domain.setHotelId(dto.getHotelId());
        domain.setUrl(dto.getUrl());
        domain.setPrimary(dto.getIsPrimary());
        return domain;
    }

    public static HotelImageResponseDTO toResponseDTO(HotelImage domain) {
        return HotelImageResponseDTO.builder()
                .id(domain.getId())
                .hotelId(domain.getHotelId())
                .url(domain.getUrl())
                .isPrimary(domain.isPrimary())
                .build();
    }
}
