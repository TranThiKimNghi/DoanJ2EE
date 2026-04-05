package com.booking.Common.infrastructure.persistence.repository;

import com.booking.Common.infrastructure.persistence.entity.NearbyPlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NearbyPlaceRepository extends JpaRepository<NearbyPlaceEntity, UUID> {
    // find near place by hotel
    List<NearbyPlaceEntity> findByHotelId(UUID hotelId);

    // Nếu muốn chỉ lấy những địa điểm chưa xóa (soft delete)
    List<NearbyPlaceEntity> findByHotelIdAndIsDeletedFalse(UUID hotelId);

}
