package com.booking.Common.adapters.dto.Reponse;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ServiceResponseDTO {
    private UUID id;
    private String name;
    private BigDecimal price;
}
