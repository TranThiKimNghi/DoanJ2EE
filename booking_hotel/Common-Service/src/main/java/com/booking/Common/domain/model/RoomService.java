package com.booking.Common.domain.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Data

public class RoomService {
    private UUID roomId;
    private UUID hotelId;
    private UUID serviceId;
    private Integer quantity;
    private LocalDateTime createdAt;
    private UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;
    private boolean isDeleted;
}
