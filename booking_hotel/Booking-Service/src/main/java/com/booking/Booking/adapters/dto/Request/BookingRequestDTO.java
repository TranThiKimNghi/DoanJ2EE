package com.booking.Booking.adapters.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {
    private UUID userId;
    private UUID hotelId;
    private UUID roomId;
    private LocalDateTime checkingDate;
    private LocalDateTime checkoutDate;

}
