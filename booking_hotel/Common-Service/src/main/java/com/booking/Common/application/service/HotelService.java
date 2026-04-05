package com.booking.Common.application.service;

import com.booking.Common.adapters.dto.HotelUpdateDTO;
import com.booking.Common.adapters.dto.Reponse.HotelResponseDTO;
import com.booking.Common.adapters.dto.Request.HotelRequestDTO;
import com.booking.Common.application.mapper.HotelMapper;
import com.booking.Common.domain.model.Hotel;
import com.booking.Common.infrastructure.persistence.entity.HotelEntity;
import com.booking.Common.infrastructure.persistence.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelResponseDTO createHotel(HotelRequestDTO request) {
        Hotel domain = HotelMapper.toDomain(request);
        domain.setId(UUID.randomUUID());
        HotelEntity entity = HotelMapper.toEntity(domain);
        HotelEntity saved = hotelRepository.save(entity);
        return HotelMapper.toResponseDTO(HotelMapper.toDomain(saved));
    }

    public HotelResponseDTO getHotelById(UUID id) {
        HotelEntity entity = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        return HotelMapper.toResponseDTO(HotelMapper.toDomain(entity));
    }

    public List<HotelResponseDTO> getByProvince(UUID provinceId) {
        return hotelRepository.findByProvinceId(provinceId)
                .stream()
                .map(HotelMapper::toDomain)
                .map(HotelMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<HotelResponseDTO> getByWard(UUID wardId) {
        return hotelRepository.findByWardId(wardId)
                .stream()
                .map(HotelMapper::toDomain)
                .map(HotelMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<HotelResponseDTO> searchByName(String name) {
        return hotelRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(HotelMapper::toDomain)
                .map(HotelMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<HotelResponseDTO> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(HotelMapper::toDomain)
                .map(HotelMapper::toResponseDTO)
                .toList();
    }

    public HotelResponseDTO updateHotel(UUID id, HotelUpdateDTO dto) {
        HotelEntity entity = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        Hotel domain = HotelMapper.toDomain(entity);

        HotelMapper.updateDomain(domain, dto);

        entity = hotelRepository.save(HotelMapper.toEntity(domain));

        return HotelMapper.toResponseDTO(HotelMapper.toDomain(entity));
    }
    public void deleteHotel(UUID id) {
        HotelEntity entity = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        entity.setIsDeleted(true);
        hotelRepository.save(entity);
    }

}
