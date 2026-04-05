package com.booking.Common.application.mapper;

import com.booking.Common.adapters.dto.Reponse.RoomServiceRequestDTO;
import com.booking.Common.adapters.dto.Request.RoomServiceResponseDTO;
import com.booking.Common.domain.model.RoomService;
import com.booking.Common.infrastructure.persistence.entity.RoomServiceEntity;

public class RoomServiceMapper {
    public static RoomService toDomain(RoomServiceEntity entity) {
        if (entity == null) return null;

        RoomService domain = new RoomService();
        domain.setRoomId(entity.getRoomId());
        domain.setHotelId(entity.getHotelId());
        domain.setServiceId(entity.getServiceId());
        domain.setQuantity(entity.getQuantity());
        return domain;
    }

    public static RoomServiceEntity toEntity(RoomService domain) {
        RoomServiceEntity entity = new RoomServiceEntity();
        entity.setRoomId(domain.getRoomId());
        entity.setHotelId(domain.getHotelId());
        entity.setServiceId(domain.getServiceId());
        entity.setQuantity(domain.getQuantity());
        return entity;
    }

    public static RoomService toDomain(RoomServiceRequestDTO dto) {
        if (dto == null) return null;

        RoomService domain = new RoomService();
        domain.setRoomId(dto.getRoomId());
        domain.setHotelId(dto.getHotelId());
        domain.setServiceId(dto.getServiceId());
        domain.setQuantity(dto.getQuantity());
        return domain;
    }

    public static RoomServiceResponseDTO toResponseDTO(RoomService domain) {
        return RoomServiceResponseDTO.builder()
                .roomId(domain.getRoomId())
                .hotelId(domain.getHotelId())
                .serviceId(domain.getServiceId())
                .quantity(domain.getQuantity())
                .build();
    }

}
