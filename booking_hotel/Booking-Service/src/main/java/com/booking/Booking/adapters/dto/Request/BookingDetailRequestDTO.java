package com.booking.Booking.adapters.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailRequestDTO {
    private UUID bookingId;
    private UUID hotelId;
    private UUID roomId;
    private BigDecimal price;
    private Integer quantity;
    private Integer guestCount;
}
