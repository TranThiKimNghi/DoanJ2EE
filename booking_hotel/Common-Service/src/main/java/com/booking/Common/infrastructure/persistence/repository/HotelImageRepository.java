package com.booking.Common.infrastructure.persistence.repository;

import com.booking.Common.infrastructure.persistence.entity.HotelImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HotelImageRepository extends JpaRepository<HotelImageEntity, UUID> {
    // find img hotel by hotel
    List<HotelImageEntity> findByHotelId(UUID hotelId);

    //find img hotel la primary
    List<HotelImageEntity> findByHotelIdAndIsPrimaryTrue(UUID hotelID);
}
