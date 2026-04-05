package com.booking.Booking.domain.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Getter
@Setter
public class BookingDetail {
    private UUID id;
    private UUID hotelId;
    private UUID bookingId;
    private UUID roomId;
    private BigDecimal price;
    private Integer guestCount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdBy;
    private UUID updatedBy;
    private boolean isDeleted;
}
