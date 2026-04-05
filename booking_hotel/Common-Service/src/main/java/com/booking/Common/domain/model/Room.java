package com.booking.Common.domain.model;

import lombok.Getter;

import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Room {
    private UUID id;
    private UUID hotelId;
    private String roomNumber;
    private String roomType;
    private BigDecimal price;
    private String status;
    private String description;
    private LocalDateTime createdAt;
    private UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;
    private boolean isDeleted;
}
