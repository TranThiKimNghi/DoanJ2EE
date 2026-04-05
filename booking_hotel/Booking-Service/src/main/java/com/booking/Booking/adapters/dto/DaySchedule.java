package com.booking.Booking.adapters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DaySchedule {
    private int dayNumber;
    private Map<String, List<PlaceDto>> sessions; // Morning, Afternoon, Evening
}
