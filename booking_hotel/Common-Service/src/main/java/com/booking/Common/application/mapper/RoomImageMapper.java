package com.booking.Common.application.mapper;

import com.booking.Common.adapters.dto.Reponse.RoomImageResponseDTO;
import com.booking.Common.adapters.dto.Request.RoomImageRequestDTO;
import com.booking.Common.domain.model.RoomImage;
import com.booking.Common.infrastructure.persistence.entity.RoomImageEntity;

public class RoomImageMapper {
    public static RoomImage toDomain(RoomImageEntity entity) {
        if (entity == null) return null;

        RoomImage domain = new RoomImage();
        domain.setId(entity.getId());
        domain.setRoomId(entity.getRoomId());
        domain.setUrl(entity.getUrl());
        domain.setPrimary(entity.getIsPrimary());
        return domain;
    }

    public static RoomImageEntity toEntity(RoomImage domain) {
        RoomImageEntity entity = new RoomImageEntity();
        entity.setId(domain.getId());
        entity.setRoomId(domain.getRoomId());
        entity.setUrl(domain.getUrl());
        entity.setIsPrimary(domain.isPrimary());
        return entity;
    }

    public static RoomImage toDomain(RoomImageRequestDTO dto) {
        if (dto == null) return null;

        RoomImage domain = new RoomImage();
        domain.setRoomId(dto.getRoomId());
        domain.setUrl(dto.getUrl());
        domain.setPrimary(dto.getIsPrimary());
        return domain;
    }

    public static RoomImageResponseDTO toResponseDTO(RoomImage domain) {
        return RoomImageResponseDTO.builder()
                .id(domain.getId())
                .roomId(domain.getRoomId())
                .url(domain.getUrl())
                .isPrimary(domain.isPrimary())
                .build();
    }
}
