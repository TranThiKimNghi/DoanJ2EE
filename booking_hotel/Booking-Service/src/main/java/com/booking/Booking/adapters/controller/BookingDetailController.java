//package com.booking.Booking.adapters.controller;
//
//import com.booking.Booking.adapters.dto.Request.BookingDetailRequestDTO;
//import com.booking.Booking.adapters.dto.Response.BookingDetailResponseDTO;
//import com.booking.Booking.application.service.BookingDetailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/api/booking-details")
//@RequiredArgsConstructor
//
//
//public class BookingDetailController {
//    private final BookingDetailService bookingDetailService;
//
//    // CREATE
//    @PostMapping
//    public ResponseEntity<BookingDetailResponseDTO> create(@RequestBody BookingDetailRequestDTO request) {
//        BookingDetailResponseDTO response = bookingDetailService.create(request);
//        return ResponseEntity.ok(response);
//    }
//
//    // GET ALL
//    @GetMapping
//    public ResponseEntity<List<BookingDetailResponseDTO>> getAll() {
//        List<BookingDetailResponseDTO> list = bookingDetailService.getAll();
//        return ResponseEntity.ok(list);
//    }
//
//    // GET BY BOOKING
//    @GetMapping("/booking/{bookingId}")
//    public ResponseEntity<List<BookingDetailResponseDTO>> getByBooking(@PathVariable UUID bookingId) {
//        List<BookingDetailResponseDTO> list = bookingDetailService.getByBooking(bookingId);
//        return ResponseEntity.ok(list);
//    }
//
//    // GET BY HOTEL
//    @GetMapping("/hotel/{hotelId}")
//    public ResponseEntity<List<BookingDetailResponseDTO>> getByHotel(@PathVariable UUID hotelId) {
//        List<BookingDetailResponseDTO> list = bookingDetailService.getByHotel(hotelId);
//        return ResponseEntity.ok(list);
//    }
//
//    // GET BY ROOM
//    @GetMapping("/room/{roomId}")
//    public ResponseEntity<List<BookingDetailResponseDTO>> getByRoom(@PathVariable UUID roomId) {
//        List<BookingDetailResponseDTO> list = bookingDetailService.getByRoom(roomId);
//        return ResponseEntity.ok(list);
//    }
//
//    // UPDATE
//    @PutMapping("/{id}")
//    public ResponseEntity<BookingDetailResponseDTO> update(
//            @PathVariable UUID id,
//            @RequestBody BookingDetailRequestDTO request
//    ) {
//        BookingDetailResponseDTO response = bookingDetailService.update(id, request);
//        return ResponseEntity.ok(response);
//    }
//
//    // SOFT DELETE
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable UUID id) {
//        bookingDetailService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//}
