package com.booking.Common.adapters.dto.Request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceRequestDTO {
    private String name;
    private BigDecimal price;
}
