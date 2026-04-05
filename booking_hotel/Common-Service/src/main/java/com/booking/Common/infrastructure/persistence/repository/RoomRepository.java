package com.booking.Common.infrastructure.persistence.repository;

import com.booking.Common.infrastructure.persistence.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<RoomEntity, UUID> {
    // find all room by hotel
    List<RoomEntity> findByHotelId(UUID hotelId);

    // find phong by hotel voi trang thai
    List<RoomEntity> findByHotelIdAndStatus(UUID hotelId, String status);

    // find room by roomtype Like hong phan biet hoa/thuong
    List<RoomEntity> findByRoomTypeContainingIgnoreCase(String roomType);
}
