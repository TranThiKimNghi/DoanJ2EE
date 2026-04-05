package com.booking.Common.application.service;
import com.booking.Common.adapters.dto.Reponse.RoomServiceRequestDTO;
import com.booking.Common.adapters.dto.Request.RoomServiceResponseDTO;
import com.booking.Common.application.mapper.RoomServiceMapper;
import com.booking.Common.domain.model.RoomService;
import com.booking.Common.infrastructure.persistence.entity.RoomServiceEntity;
import com.booking.Common.infrastructure.persistence.repository.RoomServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceManagement {
    private final RoomServiceRepository roomServiceRepository;

    // Thêm RoomService
    public RoomServiceResponseDTO createRoomService(RoomServiceRequestDTO request) {
        RoomService domain = RoomServiceMapper.toDomain(request);

        RoomServiceEntity entity = roomServiceRepository.save(RoomServiceMapper.toEntity(domain));
        return RoomServiceMapper.toResponseDTO(RoomServiceMapper.toDomain(entity));
    }

    // Lấy tất cả dịch vụ của phòng
    public List<RoomServiceResponseDTO> getServicesByRoom(UUID roomId) {
        return roomServiceRepository.findByRoomId(roomId)
                .stream()
                .map(RoomServiceMapper::toDomain)
                .map(RoomServiceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Lấy tất cả dịch vụ của khách sạn
    public List<RoomServiceResponseDTO> getServicesByHotel(UUID hotelId) {
        return roomServiceRepository.findByHotelId(hotelId)
                .stream()
                .map(RoomServiceMapper::toDomain)
                .map(RoomServiceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
