package com.booking.Booking.adapters.controller;

import com.booking.Booking.adapters.dto.BookingUpdateDTO;
import com.booking.Booking.adapters.dto.Request.BookingRequestDTO;
import com.booking.Booking.adapters.dto.Response.BookingResponseDTO;
import com.booking.Booking.application.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication; // ✅ ĐÚNG
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // ================= CREATE BOOKING =================
    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(
            @RequestBody BookingRequestDTO request,
            Authentication authentication
    ) {
        if (authentication == null) {
            throw new RuntimeException("Bạn chưa đăng nhập");
        }

        UUID userId = (UUID) authentication.getPrincipal();

        return ResponseEntity.ok(
                bookingService.createBooking(request, userId)
        );
    }

    // ================= GET MY BOOKINGS =================
    @GetMapping("/me")
    public ResponseEntity<List<BookingResponseDTO>> getMyBookings(
            Authentication authentication
    ) {
        if (authentication == null) {
            throw new RuntimeException("Bạn chưa đăng nhập");
        }

        UUID userId = (UUID) authentication.getPrincipal(); // ✅ CHUẨN

        return ResponseEntity.ok(
                bookingService.getBookingsForCurrentUser(userId)
        );
    }

    // ================= ADMIN: GET ALL =================
    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // ================= UPDATE STATUS =================
    @PutMapping("/{id}/status")
    public ResponseEntity<BookingResponseDTO> updateBookingStatus(
            @PathVariable UUID id,
            @RequestParam String status
    ) {
        return ResponseEntity.ok(
                bookingService.updateBookingStatus(id, status)
        );
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable UUID id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successfully");
    }
}
