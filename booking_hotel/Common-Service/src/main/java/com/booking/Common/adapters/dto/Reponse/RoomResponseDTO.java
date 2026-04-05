package com.booking.Common.adapters.dto.Reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class RoomResponseDTO {
    private UUID id;
    private UUID hotelId;
    private String roomNumber;
    private String roomType;
    private BigDecimal price;
    private String status;
    private String description;

}
