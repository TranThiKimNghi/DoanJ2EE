package com.booking.Booking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private UUID id;
    private UUID userId;
    private UUID hotelId;
    private UUID roomId;
    private LocalDateTime checkingDate;
    private LocalDateTime checkoutDate;
    private String status; // pending / confirmed / cancelled / completed
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}
