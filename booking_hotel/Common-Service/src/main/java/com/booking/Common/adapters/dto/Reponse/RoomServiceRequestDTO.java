package com.booking.Common.adapters.dto.Reponse;

import lombok.Data;

import java.util.UUID;

@Data
public class RoomServiceRequestDTO {
    private UUID roomId;
    private UUID hotelId;
    private UUID serviceId;
    private Integer quantity;
}
