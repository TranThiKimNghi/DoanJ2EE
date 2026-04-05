package com.booking.Common.application.service;

import com.booking.Common.adapters.dto.Reponse.RoomImageResponseDTO;
import com.booking.Common.adapters.dto.Request.RoomImageRequestDTO;
import com.booking.Common.application.mapper.RoomImageMapper;
import com.booking.Common.domain.model.RoomImage;
import com.booking.Common.infrastructure.persistence.entity.RoomImageEntity;
import com.booking.Common.infrastructure.persistence.repository.RoomImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomImageService {
    private final RoomImageRepository roomImageRepository;

    // Lấy tất cả ảnh của một phòng
    public List<RoomImageResponseDTO> getImagesByRoom(UUID roomId) {
        List<RoomImageEntity> images = roomImageRepository.findByRoomId(roomId);
        return images.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Chuyển entity sang DTO
    private RoomImageResponseDTO toResponseDTO(RoomImageEntity entity) {
        return RoomImageResponseDTO.builder()
                .id(entity.getId())
                .roomId(entity.getRoomId())
                .url(entity.getUrl())
                .isPrimary(entity.getIsPrimary())
                .build();
    }

    // Thêm ảnh
    @Transactional
    public RoomImageResponseDTO createRoomImage(RoomImageRequestDTO request) {
        RoomImage domain = RoomImageMapper.toDomain(request);
        domain.setId(UUID.randomUUID());

        // Nếu ảnh primary thì set tất cả ảnh khác của room thành false
        if (Boolean.TRUE.equals(domain.isPrimary())) {
            List<RoomImageEntity> existing = roomImageRepository.findByRoomId(domain.getRoomId());
            for (RoomImageEntity img : existing) {
                img.setIsPrimary(false);
            }
            roomImageRepository.saveAll(existing);
        }

        RoomImageEntity saved = roomImageRepository.save(RoomImageMapper.toEntity(domain));
        return RoomImageMapper.toResponseDTO(RoomImageMapper.toDomain(saved));
    }

    // Xóa ảnh
    public void deleteRoomImage(UUID id) {
        if (!roomImageRepository.existsById(id)) {
            throw new RuntimeException("Room image not found");
        }
        roomImageRepository.deleteById(id);
    }
}
