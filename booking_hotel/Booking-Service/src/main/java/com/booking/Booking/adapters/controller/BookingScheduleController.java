package com.booking.Booking.adapters.controller;

import com.booking.Booking.adapters.dto.Itinerary;
import com.booking.Booking.application.service.BookingScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingScheduleController {
    private final BookingScheduleService scheduleService;

    public BookingScheduleController(BookingScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{bookingId}/itinerary")
    public ResponseEntity<Itinerary> getItinerary(@PathVariable UUID bookingId) {
        Itinerary itinerary = scheduleService.generateItinerary(bookingId);
        return ResponseEntity.ok(itinerary);
    }
}
