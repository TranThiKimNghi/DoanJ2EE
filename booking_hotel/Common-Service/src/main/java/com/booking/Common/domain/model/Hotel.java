package com.booking.Common.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
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
    private UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;
    private boolean isDeleted;
}

