package com.booking.Booking.adapters.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {
    private UUID id;
    private UUID userId;
    private String userFullname;
    private UUID hotelId;
    private String hotelName;
    private UUID roomId;
    private String roomNumber;
    private LocalDateTime checkingDate;
    private LocalDateTime checkoutDate;
    private String status;
    private BigDecimal totalAmount;
}
