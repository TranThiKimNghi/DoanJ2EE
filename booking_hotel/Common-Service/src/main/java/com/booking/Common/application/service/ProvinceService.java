package com.booking.Common.application.service;

import com.booking.Common.adapters.dto.Reponse.ProvinceResponseDTO;
import com.booking.Common.adapters.dto.Request.ProvinceRequestDTO;
import com.booking.Common.application.mapper.ProvinceMapper;
import com.booking.Common.domain.model.Province;
import com.booking.Common.infrastructure.persistence.entity.ProvinceEntity;
import com.booking.Common.infrastructure.persistence.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProvinceService {
    private final ProvinceRepository provinceRepository;

    public ProvinceResponseDTO createProvince(ProvinceRequestDTO request) {
        Province domain = ProvinceMapper.toDomain(request);
        domain.setId(UUID.randomUUID());

        ProvinceEntity entity = ProvinceMapper.toEntity(domain);

        ProvinceEntity saved = provinceRepository.save(entity);

        return ProvinceMapper.toResponseDTO(ProvinceMapper.toDomain(saved));
    }

    public List<ProvinceResponseDTO> getAll() {
        return provinceRepository.findAll().stream()
                .map(ProvinceMapper::toDomain)
                .map(ProvinceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ProvinceResponseDTO getById(UUID id) {
        ProvinceEntity entity = provinceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Province not found"));
        return ProvinceMapper.toResponseDTO(ProvinceMapper.toDomain(entity));
    }

    public ProvinceResponseDTO updateProvince(UUID id, ProvinceRequestDTO request) {
        ProvinceEntity entity = provinceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Province not found"));

        entity.setName(request.getName());
        entity.setUpdatedAt(java.time.LocalDateTime.now());

        ProvinceEntity updated = provinceRepository.save(entity);

        return ProvinceMapper.toResponseDTO(ProvinceMapper.toDomain(updated));
    }

    public void deleteProvince(UUID id) {
        ProvinceEntity entity = provinceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Province not found"));

        entity.setIsDeleted(true);
        entity.setUpdatedAt(java.time.LocalDateTime.now());

        provinceRepository.save(entity);
    }
}
