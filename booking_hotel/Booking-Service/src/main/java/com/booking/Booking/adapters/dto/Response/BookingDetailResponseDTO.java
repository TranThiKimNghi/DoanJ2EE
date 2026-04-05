package com.booking.Booking.adapters.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDetailResponseDTO {
    private UUID id;
    private UUID bookingId;
    private UUID hotelId;
    private UUID roomId;
    private BigDecimal price;
    private Integer guestCount;
    private String status;
}
