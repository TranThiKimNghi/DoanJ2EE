package com.booking.Common.application.service;

import com.booking.Common.adapters.dto.Reponse.RoomResponseDTO;
import com.booking.Common.adapters.dto.Request.RoomRequestDTO;
import com.booking.Common.application.mapper.RoomMapper;
import com.booking.Common.domain.model.Room;
import com.booking.Common.infrastructure.persistence.entity.RoomEntity;
import com.booking.Common.infrastructure.persistence.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class RoomService {
    private final RoomRepository roomRepository;

    // CREATE
    public RoomResponseDTO createRoom(RoomRequestDTO request) {
        Room domain = RoomMapper.toDomain(request);
        domain.setId(UUID.randomUUID());

        RoomEntity saved = roomRepository.save(RoomMapper.toEntity(domain));
        return RoomMapper.toResponseDTO(RoomMapper.toDomain(saved));
    }

    // GET by ID
    public RoomResponseDTO getRoomById(UUID id) {
        RoomEntity entity = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        return RoomMapper.toResponseDTO(RoomMapper.toDomain(entity));
    }

    // GET all rooms
    public List<RoomResponseDTO> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(RoomMapper::toDomain)
                .map(RoomMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // GET all rooms by hotel
    public List<RoomResponseDTO> getRoomsByHotel(UUID hotelId) {
        return roomRepository.findByHotelId(hotelId)
                .stream()
                .map(RoomMapper::toDomain)
                .map(RoomMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    public RoomResponseDTO updateRoom(UUID id, RoomRequestDTO request) {
        RoomEntity existing = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Room domain = RoomMapper.toDomain(request);
        domain.setId(existing.getId());

        RoomEntity updated = roomRepository.save(RoomMapper.toEntity(domain));
        return RoomMapper.toResponseDTO(RoomMapper.toDomain(updated));
    }

    // DELETE
    public void deleteRoom(UUID id) {
        roomRepository.deleteById(id);
    }
}
