package com.booking.Common.application.service;

import com.booking.Common.adapters.dto.Reponse.WardResponseDTO;
import com.booking.Common.application.mapper.WardMapper;
import com.booking.Common.infrastructure.persistence.repository.WardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WardService {
    private final WardRepository wardRepository;
    public List<WardResponseDTO> getAll() {
        return wardRepository.findAll()
                .stream()
                .filter(e -> Boolean.FALSE.equals(e.getIsDeleted()))
                .map(e -> WardMapper.toResponseDTO(WardMapper.toDomain(e)))
                .toList();
    }

    public List<WardResponseDTO> getByProvinceSorted(UUID provinceId) {
        return wardRepository.findByProvinceIdOrderByNameAsc(provinceId)
                .stream()
                .filter(e -> Boolean.FALSE.equals(e.getIsDeleted()))
                .map(e -> WardMapper.toResponseDTO(WardMapper.toDomain(e)))
                .toList();
    }

    public List<WardResponseDTO> searchByNameInProvince(UUID provinceId, String name) {
        return wardRepository.findByProvinceIdAndNameContainingIgnoreCaseOrderByNameAsc(provinceId, name)
                .stream()
                .filter(e -> Boolean.FALSE.equals(e.getIsDeleted()))
                .map(e -> WardMapper.toResponseDTO(WardMapper.toDomain(e)))
                .toList();
    }
}