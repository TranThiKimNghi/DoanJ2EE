package com.booking.Common.adapters.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearbyPlaceDTO {
    private UUID id;
    private String placeName;
    private String category;
    private BigDecimal rating;
    private Integer numReviews;
    private Float distance;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Double score;
}
