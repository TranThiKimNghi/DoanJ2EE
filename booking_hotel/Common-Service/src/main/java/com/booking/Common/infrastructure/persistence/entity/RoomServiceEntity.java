package com.booking.Common.infrastructure.persistence.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room_services")
@EqualsAndHashCode(callSuper = true)
public class RoomServiceEntity extends BaseEntity{
    @Column(name = "room_id", nullable = false)
    private UUID roomId;

    @Column(name = "service_id", nullable = false)
    private UUID serviceId;

    @Column(name = "hotel_id", nullable = false)
    private UUID hotelId;

    @Column(name = "quantity")
    private Integer quantity = 1;
}
