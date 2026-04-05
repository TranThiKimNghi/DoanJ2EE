package com.booking.Booking.infrastructure.persistence.repository;

import com.booking.Booking.infrastructure.persistence.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    //  find all booking by user
    List<BookingEntity> findAllByIsDeletedFalse();
    List<BookingEntity> findByUserIdAndIsDeletedFalse(UUID userId);
}
