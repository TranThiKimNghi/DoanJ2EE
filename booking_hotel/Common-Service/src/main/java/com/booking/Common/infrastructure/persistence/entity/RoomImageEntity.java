package com.booking.Common.infrastructure.persistence.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room_images")
@EqualsAndHashCode(callSuper = true)
public class RoomImageEntity extends BaseEntity {
    @Column(name = "room_id", nullable = false)
    private UUID roomId;

    @Column(name = "url", nullable = false)
    private String url;

    @Builder.Default
    @Column(name = "is_primary")
    private Boolean isPrimary = false;
}
