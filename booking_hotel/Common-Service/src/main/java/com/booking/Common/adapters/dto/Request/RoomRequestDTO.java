package com.booking.Common.adapters.dto.Request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data

public class RoomRequestDTO {
    private UUID hotelId;
    private String roomNumber;
    private String roomType;
    private BigDecimal price;
    private String status; // available / booked / maintenance
    private String description;
}
