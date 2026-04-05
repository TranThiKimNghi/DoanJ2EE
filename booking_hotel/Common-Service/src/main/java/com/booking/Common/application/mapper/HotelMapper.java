package com.booking.Common.application.mapper;


import com.booking.Common.adapters.dto.HotelUpdateDTO;
import com.booking.Common.adapters.dto.Reponse.HotelResponseDTO;
import com.booking.Common.adapters.dto.Request.HotelRequestDTO;
import com.booking.Common.domain.model.Hotel;
import com.booking.Common.infrastructure.persistence.entity.HotelEntity;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {
    // ===================== Entity → Domain =====================
    public static Hotel toDomain(HotelEntity entity) {
        if (entity == null) return null;

        return new Hotel(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getProvinceId(),
                entity.getWardId(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getRating(),
                entity.getViewCount(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getUpdatedAt(),
                entity.getUpdatedBy(),
                entity.getIsDeleted()
        );
    }

    // ===================== Domain → Entity =====================
    public static HotelEntity toEntity(Hotel domain) {
        if (domain == null) return null;

        HotelEntity entity = new HotelEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setAddress(domain.getAddress());
        entity.setProvinceId(domain.getProvinceId());
        entity.setWardId(domain.getWardId());
        entity.setLatitude(domain.getLatitude());
        entity.setLongitude(domain.getLongitude());
        entity.setPhone(domain.getPhone());
        entity.setEmail(domain.getEmail());
        entity.setRating(domain.getRating());
        entity.setViewCount(domain.getViewCount());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setCreatedBy(domain.getCreatedBy());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setUpdatedBy(domain.getUpdatedBy());
        entity.setIsDeleted(domain.isDeleted());

        return entity;
    }

    // ===================== Domain → ResponseDTO =====================
    public static HotelResponseDTO toResponseDTO(Hotel domain) {
        if (domain == null) return null;

        return HotelResponseDTO.builder()
                .id(domain.getId())
                .name(domain.getName())
                .address(domain.getAddress())
                .provinceId(domain.getProvinceId())
                .wardId(domain.getWardId())
                .latitude(domain.getLatitude())
                .longitude(domain.getLongitude())
                .phone(domain.getPhone())
                .email(domain.getEmail())
                .rating(domain.getRating())
                .viewCount(domain.getViewCount())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();

    }

    // ===================== RequestDTO → Domain =====================
    public static Hotel toDomain(HotelRequestDTO dto) {
        if (dto == null) return null;

        return Hotel.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .provinceId(dto.getProvinceId())
                .wardId(dto.getWardId())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .rating(dto.getRating())
                .viewCount(0)
                .isDeleted(false)
                .build();
    }
    public static void updateDomain(Hotel domain, HotelUpdateDTO dto) {
        if (dto.getName() != null) domain.setName(dto.getName());
        if (dto.getAddress() != null) domain.setAddress(dto.getAddress());
        if (dto.getProvinceId() != null) domain.setProvinceId(dto.getProvinceId());
        if (dto.getWardId() != null) domain.setWardId(dto.getWardId());
        if (dto.getLatitude() != null) domain.setLatitude(dto.getLatitude());
        if (dto.getLongitude() != null) domain.setLongitude(dto.getLongitude());
        if (dto.getPhone() != null) domain.setPhone(dto.getPhone());
        if (dto.getEmail() != null) domain.setEmail(dto.getEmail());
        if (dto.getRating() != null) domain.setRating(dto.getRating());
    }
}