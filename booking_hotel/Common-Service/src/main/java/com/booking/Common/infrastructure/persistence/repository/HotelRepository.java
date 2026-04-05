package com.booking.Common.infrastructure.persistence.repository;

import com.booking.Common.infrastructure.persistence.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<HotelEntity, UUID> {
    //Lấy all hotel in 1 province
    List<HotelEntity> findByProvinceId(UUID province_id);

    // find all hotel in a ward
    List<HotelEntity> findByWardId(UUID wardId);

    // Tìm theo tên Like không phân biệt hoa/thường
    List<HotelEntity> findByNameContainingIgnoreCase(String name);
}
