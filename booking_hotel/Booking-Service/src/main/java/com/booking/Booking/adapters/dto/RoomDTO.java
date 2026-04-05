package com.booking.Booking.adapters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private UUID id;
    private UUID hotelId;
    private String roomNumber;
    private String roomType;
    private BigDecimal price;
    private String status;
}
