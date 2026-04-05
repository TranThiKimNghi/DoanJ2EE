package com.booking.Common.application.mapper;

import com.booking.Common.adapters.dto.Reponse.RoomResponseDTO;
import com.booking.Common.adapters.dto.Request.RoomRequestDTO;
import com.booking.Common.domain.model.Room;
import com.booking.Common.infrastructure.persistence.entity.RoomEntity;

public class RoomMapper {
    public static Room toDomain(RoomEntity entity) {
        if (entity == null) return null;

        Room domain = new Room();
        domain.setId(entity.getId());
        domain.setHotelId(entity.getHotelId());
        domain.setRoomNumber(entity.getRoomNumber());
        domain.setRoomType(entity.getRoomType());
        domain.setPrice(entity.getPrice());
        domain.setStatus(entity.getStatus());
        domain.setDescription(entity.getDescription());
        return domain;
    }

    public static RoomEntity toEntity(Room domain) {
        RoomEntity entity = new RoomEntity();
        entity.setId(domain.getId());
        entity.setHotelId(domain.getHotelId());
        entity.setRoomNumber(domain.getRoomNumber());
        entity.setRoomType(domain.getRoomType());
        entity.setPrice(domain.getPrice());
        entity.setStatus(domain.getStatus());
        entity.setDescription(domain.getDescription());
        return entity;
    }

    public static Room toDomain(RoomRequestDTO dto) {
        if (dto == null) return null;

        Room domain = new Room();
        domain.setHotelId(dto.getHotelId());
        domain.setRoomNumber(dto.getRoomNumber());
        domain.setRoomType(dto.getRoomType());
        domain.setPrice(dto.getPrice());
        domain.setStatus(dto.getStatus());
        domain.setDescription(dto.getDescription());
        return domain;
    }

    public static RoomResponseDTO toResponseDTO(Room domain) {
        return RoomResponseDTO.builder()
                .id(domain.getId())
                .hotelId(domain.getHotelId())
                .roomNumber(domain.getRoomNumber())
                .roomType(domain.getRoomType())
                .price(domain.getPrice())
                .status(domain.getStatus())
                .description(domain.getDescription())
                .build();
    }
}
