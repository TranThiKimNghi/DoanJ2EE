package com.booking.Booking.application.service;

import com.booking.Booking.adapters.dto.Request.BookingRequestDTO;
import com.booking.Booking.adapters.dto.Response.BookingResponseDTO;
import com.booking.Booking.application.mapper.BookingMapper;
import com.booking.Booking.infrastructure.persistence.entity.BookingDetailsEntity;
import com.booking.Booking.infrastructure.persistence.entity.BookingEntity;
import com.booking.Booking.infrastructure.persistence.repository.BookingDetailsRepository;
import com.booking.Booking.infrastructure.persistence.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingDetailsRepository bookingDetailsRepository;
    private final RoomServiceClient roomServiceClient;
    private final HotelServiceClient hotelServiceClient;

    // ================= CREATE BOOKING =================
    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO request, UUID userId) {
        var room = roomServiceClient.getRoomById(request.getRoomId());
        if (room == null || room.getId() == null) throw new RuntimeException("Room not found");
        if (!"available".equalsIgnoreCase(room.getStatus())) throw new RuntimeException("Room is not available");

        var hotel = hotelServiceClient.getHotelById(request.getHotelId());
        if (hotel == null || hotel.getId() == null) throw new RuntimeException("Hotel not found");
        if (!room.getHotelId().equals(hotel.getId())) throw new RuntimeException("HotelId does not match room's hotel");

        BookingEntity booking = BookingEntity.builder()
                .userId(userId)
                .hotelId(hotel.getId())
                .roomId(room.getId())
                .checkingDate(request.getCheckingDate())
                .checkoutDate(request.getCheckoutDate())
                .status("pending")
                .totalAmount(room.getPrice())
                .isDeleted(false)
                .build();
        booking = bookingRepository.save(booking);

        BookingDetailsEntity detail = BookingDetailsEntity.builder()
                .booking(booking)
                .hotelId(hotel.getId())
                .roomId(room.getId())
                .price(room.getPrice())
                .guestCount(1)
                .status("booked")
                .isDeleted(false)
                .build();
        bookingDetailsRepository.save(detail);

        return BookingMapper.toResponseDTO(
                booking,
                detail,
                hotel.getName(),
                room.getRoomNumber()
        );
    }

    // ================= GET MY BOOKINGS =================
    public List<BookingResponseDTO> getBookingsForCurrentUser(UUID userId) {
        return bookingRepository.findByUserIdAndIsDeletedFalse(userId)
                .stream()
                .map(b -> {
                    var detail = bookingDetailsRepository
                            .findByBookingIdAndIsDeletedFalse(b.getId())
                            .stream()
                            .findFirst()
                            .orElse(null);

                    String hotelName = detail != null ? hotelServiceClient.getHotelNameById(detail.getHotelId()) : "";
                    String roomNumber = detail != null ? roomServiceClient.getRoomNumberById(detail.getRoomId()) : "";

                    return BookingMapper.toResponseDTO(b, detail, hotelName, roomNumber);
                })
                .toList();
    }

    // ================= UPDATE STATUS =================
    @Transactional
    public BookingResponseDTO updateBookingStatus(UUID bookingId, String status) {
        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(status);
        bookingRepository.save(booking);

        BookingDetailsEntity detail = bookingDetailsRepository
                .findByBookingIdAndIsDeletedFalse(bookingId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Booking detail not found"));

        detail.setStatus(status.equalsIgnoreCase("cancelled") ? "available" : "booked");
        bookingDetailsRepository.save(detail);

        String hotelName = hotelServiceClient.getHotelNameById(detail.getHotelId());
        String roomNumber = roomServiceClient.getRoomNumberById(detail.getRoomId());

        return BookingMapper.toResponseDTO(booking, detail, hotelName, roomNumber);
    }

    // ================= DELETE =================
    @Transactional
    public void deleteBooking(UUID bookingId) {
        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setIsDeleted(true);
        bookingRepository.save(booking);

        bookingDetailsRepository.findByBookingIdAndIsDeletedFalse(bookingId)
                .forEach(d -> {
                    d.setIsDeleted(true);
                    bookingDetailsRepository.save(d);
                });
    }

    // ================= ADMIN =================
    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAllByIsDeletedFalse()
                .stream()
                .map(b -> {
                    var detail = bookingDetailsRepository
                            .findByBookingIdAndIsDeletedFalse(b.getId())
                            .stream()
                            .findFirst()
                            .orElse(null);

                    String hotelName = detail != null ? hotelServiceClient.getHotelNameById(detail.getHotelId()) : "";
                    String roomNumber = detail != null ? roomServiceClient.getRoomNumberById(detail.getRoomId()) : "";

                    return BookingMapper.toResponseDTO(b, detail, hotelName, roomNumber);
                })
                .toList();
    }
}
