package com.booking.Common.adapters.dto.Reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class WardResponseDTO {
    private UUID id;
    private String name;
    private UUID provinceId;
}
