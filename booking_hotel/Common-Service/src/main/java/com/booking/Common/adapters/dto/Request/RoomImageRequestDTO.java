package com.booking.Common.adapters.dto.Request;

import lombok.Data;

import java.util.UUID;
@Data

public class RoomImageRequestDTO {
    private UUID roomId;
    private String url;
    private Boolean isPrimary;
}
