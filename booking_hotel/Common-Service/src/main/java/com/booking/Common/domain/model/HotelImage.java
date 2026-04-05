package com.booking.Common.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelImage {
    private UUID id;
    private UUID hotelId;
    private String url;
    private boolean isPrimary;
    private LocalDateTime createdAt;
    private UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;
    private boolean isDeleted;
}
