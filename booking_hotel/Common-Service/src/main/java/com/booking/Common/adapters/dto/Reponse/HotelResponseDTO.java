package com.booking.Common.adapters.dto.Reponse;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
public class HotelResponseDTO {
    private UUID id;
    private String name;
    private String address;
    private UUID provinceId;
    private UUID wardId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String phone;
    private String email;
    private BigDecimal rating;
    private Integer viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
