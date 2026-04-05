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
@Table(name = "rooms")
@EqualsAndHashCode(callSuper = true)
public class RoomEntity extends BaseEntity{

    @Column(name = "hotel_id", nullable = false)
    private UUID hotelId;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "status")
    private String status = "available";

    @Column(name = "description")
    private String description;

}
