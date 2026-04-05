package com.booking.Booking.infrastructure.persistence.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking_details")
public class BookingDetailsEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private BookingEntity booking;
    @Column(name = "hotel_id", nullable = false)
    private UUID hotelId;
    @Column(name = "room_id", nullable = false)
    private UUID roomId;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "guest_count")
    private Integer guestCount = 1;

    @Column(name = "status")
    private String status = "booked";

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
