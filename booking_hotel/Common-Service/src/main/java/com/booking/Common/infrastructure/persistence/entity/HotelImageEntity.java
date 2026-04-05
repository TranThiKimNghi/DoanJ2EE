package com.booking.Common.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel_images")
@EqualsAndHashCode(callSuper = true)
public class HotelImageEntity extends BaseEntity {

    @Column(name = "hotel_id", nullable = false)
    private UUID hotelId;

    @Column(name = "url", nullable = false)
    private String url;
    @Builder.Default
    @Column(name = "is_primary")
    private Boolean isPrimary = false;
}
