package com.booking.Common.adapters.dto.Reponse;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProvinceResponseDTO {
    private UUID id;
    private String name;
}
