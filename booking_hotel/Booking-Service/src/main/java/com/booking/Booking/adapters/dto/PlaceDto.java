package com.booking.Booking.adapters.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlaceDto {
    private String id;
    private String placeName;
    private String category;
    private BigDecimal rating;
    private Integer numReviews;
    private String address;
    private Float distance;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private double score;
}
