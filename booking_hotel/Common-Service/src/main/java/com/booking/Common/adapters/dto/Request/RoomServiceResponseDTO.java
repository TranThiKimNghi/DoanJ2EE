package com.booking.Common.adapters.dto.Request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class RoomServiceResponseDTO {
    private UUID roomId;
    private UUID hotelId;
    private UUID serviceId;
    private Integer quantity;
}
