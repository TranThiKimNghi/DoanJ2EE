package com.booking.Common.adapters.controller;

import com.booking.Common.adapters.dto.NearbyPlaceDTO;
import com.booking.Common.application.service.NearbyPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/nearby")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NearbyPlaceController {
    @Autowired
    private NearbyPlaceService nearbyPlaceService;

    @GetMapping("/{hotelId}/top-nearby")
    public List<NearbyPlaceDTO> getTopNearbyPlaces(
            @PathVariable UUID hotelId,
            @RequestParam(defaultValue = "10") int topN) {
        return nearbyPlaceService.getTopNearbyPlacesByHotelId(hotelId, topN);
    }
}
