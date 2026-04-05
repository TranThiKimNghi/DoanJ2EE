package com.booking.Common.application.service;

import com.booking.Common.adapters.dto.Reponse.ServiceResponseDTO;
import com.booking.Common.adapters.dto.Request.ServiceRequestDTO;
import com.booking.Common.application.mapper.ServiceMapper;
import com.booking.Common.infrastructure.persistence.entity.ServiceEntity;
import com.booking.Common.infrastructure.persistence.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceManagement {
    private final ServiceRepository serviceRepository;

    // Tạo service
    public ServiceResponseDTO create(ServiceRequestDTO request) {
        com.booking.Common.domain.model.Service domain =
                ServiceMapper.toDomain(request);
        domain.setId(UUID.randomUUID());

        ServiceEntity saved = serviceRepository.save(ServiceMapper.toEntity(domain));

        return ServiceMapper.toResponseDTO(ServiceMapper.toDomain(saved));
    }
    // get all
    public List<ServiceResponseDTO> getAll() {
        return serviceRepository.findAll()
                .stream()
                .map(ServiceMapper::toDomain)
                .map(ServiceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Lấy 1 service
    public ServiceResponseDTO getById(UUID id) {
        ServiceEntity entity = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        return ServiceMapper.toResponseDTO(ServiceMapper.toDomain(entity));
    }

    // Xóa service
    public void delete(UUID id) {
        serviceRepository.deleteById(id);
    }

    // Cập nhật service
    public ServiceResponseDTO update(UUID id, ServiceRequestDTO request) {
        ServiceEntity entity = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        entity.setName(request.getName());
        entity.setPrice(request.getPrice());

        ServiceEntity updated = serviceRepository.save(entity);

        return ServiceMapper.toResponseDTO(ServiceMapper.toDomain(updated));
    }
}
