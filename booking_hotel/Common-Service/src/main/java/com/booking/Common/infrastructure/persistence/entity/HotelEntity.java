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
@Table(name = "hotels")
@EqualsAndHashCode(callSuper = true)
public class HotelEntity extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "province_id")
    private UUID provinceId;

    @Column(name = "ward_id")
    private UUID wardId;

    @Column(name = "latitude", precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 9, scale = 6)
    private BigDecimal longitude;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "rating", precision = 2, scale = 1)
    private BigDecimal rating;

    @Builder.Default
    @Column(name = "view_count")
    private Integer viewCount = 0;

}
