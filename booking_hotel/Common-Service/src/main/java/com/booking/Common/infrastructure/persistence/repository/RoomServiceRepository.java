package com.booking.Common.infrastructure.persistence.repository;

import com.booking.Common.infrastructure.persistence.entity.RoomServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoomServiceRepository extends JpaRepository<RoomServiceEntity, UUID> {
    // find Room service by room
    List<RoomServiceEntity> findByRoomId(UUID roomId);

    //find room service by hotel
    List<RoomServiceEntity> findByHotelId(UUID hotelID);

    // find room service by service
    List<RoomServiceEntity> findByServiceId(UUID ServiceId);
}
