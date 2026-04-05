package com.booking.Booking.infrastructure.persistence.repository;

import com.booking.Booking.infrastructure.persistence.entity.BookingDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingDetailsRepository extends JpaRepository<BookingDetailsEntity, UUID> {
    // Lấy detail theo booking
    List<BookingDetailsEntity> findByBookingIdAndIsDeletedFalse(UUID bookingId);

    // Lấy detail theo room
    List<BookingDetailsEntity> findByRoomIdAndIsDeletedFalse(UUID roomId);

    // Kiểm tra phòng đã được book chưa
    boolean existsByRoomIdAndStatusAndIsDeletedFalse(UUID roomId, String status);
    List<BookingDetailsEntity> findAllByBookingIdAndIsDeletedFalse(UUID bookingId);
}
