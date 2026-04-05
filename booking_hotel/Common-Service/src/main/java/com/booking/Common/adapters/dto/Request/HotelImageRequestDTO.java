package com.booking.Common.adapters.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelImageRequestDTO {
    private UUID hotelId;
    private String url;
    private Boolean isPrimary;
}
