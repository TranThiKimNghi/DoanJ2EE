package com.booking.Booking.infrastructure.persistence.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class BookingEntity{

    @Id
    @GeneratedValue(generator =  "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "hotel_id", nullable = false)
    private UUID hotelId;
    @Column(name = "room_id", nullable = false)
    private UUID roomId;
    @Column(name = "checking_date", nullable = false)
    private LocalDateTime checkingDate;

    @Column(name = "checkout_date", nullable = false)
    private LocalDateTime checkoutDate;

    @Column(name = "status")
    private String status = "pending";

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
