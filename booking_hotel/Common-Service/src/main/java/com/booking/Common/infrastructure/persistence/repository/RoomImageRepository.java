package com.booking.Common.infrastructure.persistence.repository;

import com.booking.Common.infrastructure.persistence.entity.RoomImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoomImageRepository extends JpaRepository<RoomImageEntity, UUID> {
    //find img room by room
    List<RoomImageEntity> findByRoomId(UUID roomId);

    // find img room la primary
    List<RoomImageEntity> findByRoomIdAndIsPrimaryTrue(UUID roomId);
}
