package com.booking.Common.application.service;

import com.booking.Common.adapters.dto.Reponse.HotelImageResponseDTO;
import com.booking.Common.adapters.dto.Request.HotelImageRequestDTO;
import com.booking.Common.application.mapper.HotelImageMapper;
import com.booking.Common.domain.model.HotelImage;
import com.booking.Common.infrastructure.persistence.entity.HotelImageEntity;
import com.booking.Common.infrastructure.persistence.repository.HotelImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelImageService {
    private final HotelImageRepository hotelImageRepository;
    public List<HotelImageResponseDTO> getImagesByHotel(UUID hotelId) {
        List<HotelImageEntity> images = hotelImageRepository.findByHotelId(hotelId);
        return images.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private HotelImageResponseDTO toResponseDTO(HotelImageEntity entity) {
        HotelImageResponseDTO dto = new HotelImageResponseDTO();
        dto.setId(entity.getId());
        dto.setHotelId(entity.getHotelId());
        dto.setUrl(entity.getUrl());
        return dto;
    }
    public HotelImageResponseDTO createHotelImage(HotelImageRequestDTO request) {
        HotelImage domain = HotelImageMapper.toDomain(request);
        domain.setId(UUID.randomUUID());

        HotelImageEntity saved = hotelImageRepository.save(HotelImageMapper.toEntity(domain));
        return HotelImageMapper.toResponseDTO(HotelImageMapper.toDomain(saved));
    }

    public void deleteHotelImage(UUID id) {
        if (!hotelImageRepository.existsById(id)) {
            throw new RuntimeException("Hotel image not found");
        }
        hotelImageRepository.deleteById(id);
    }
}
