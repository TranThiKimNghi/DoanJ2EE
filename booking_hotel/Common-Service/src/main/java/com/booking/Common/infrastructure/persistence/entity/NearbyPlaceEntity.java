package com.booking.Common.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nearby_places")
@EqualsAndHashCode(callSuper = true)
public class NearbyPlaceEntity extends BaseEntity {
    @Column(name = "hotel_id", nullable = false)
    private UUID hotelId;

    @Column(name = "place_name", nullable = false)
    private String placeName;

    @Column(name = "category")
    private String category;

    @Column(name = "rating", precision = 2, scale = 1)
    private BigDecimal rating;

    @Column(name = "num_reviews")
    private Integer numReviews;

    @Column(name = "address")
    private String address;

    @Column(name = "distance")
    private Float distance;

    @Column(name = "latitude", precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 9, scale = 6)
    private BigDecimal longitude;
}
