package com.booking.Booking.application.service;

import com.booking.Booking.adapters.dto.DaySchedule;
import com.booking.Booking.adapters.dto.Itinerary;
import com.booking.Booking.adapters.dto.PlaceDto;
import com.booking.Booking.infrastructure.persistence.entity.BookingEntity;
import com.booking.Booking.infrastructure.persistence.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingScheduleService {
    private final BookingRepository bookingRepository;
    private final CommonServiceClient commonClient;

    public Itinerary generateItinerary(UUID bookingId) {
        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        List<PlaceDto> topPlaces =
                commonClient.getTopNPlaces(booking.getHotelId(), 50);

        List<DaySchedule> days = GeneticAlgorithm.generateItinerary(
                topPlaces,
                booking.getCheckingDate(),
                booking.getCheckoutDate()
        );

        return new Itinerary(days);
    }
}
