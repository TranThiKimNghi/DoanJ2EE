package com.booking.Common.adapters.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WardRequestDTO {
    private String name;
    private UUID provinceId;
}
