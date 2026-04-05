package com.booking.Common.domain.model;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class NearbyPlace {
    private UUID id;
    private UUID hotelId;
    private String placeName;
    private String category;
    private BigDecimal rating;
    private Integer numReviews;
    private String address;
    private Float distance;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime createdAt;
    private UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;
    private boolean isDeleted;
}
