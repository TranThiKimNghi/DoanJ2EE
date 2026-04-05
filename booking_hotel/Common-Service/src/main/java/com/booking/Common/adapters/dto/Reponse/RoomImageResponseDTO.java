package com.booking.Common.adapters.dto.Reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor  // Constructor đầy đủ public
@NoArgsConstructor
public class RoomImageResponseDTO {
    private UUID id;
    private UUID roomId;
    private String url;
    private Boolean isPrimary;
}
