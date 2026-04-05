package com.booking.Booking.application.service;

import com.booking.Booking.adapters.dto.PlaceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CommonServiceClient {
    private final RestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8082/api/nearby";

    public CommonServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PlaceDto> getTopNPlaces(UUID placeId, int topN) {
        String url = String.format("%s/%s/top-nearby?topN=%d", BASE_URL, placeId, topN);
        ResponseEntity<PlaceDto[]> response = restTemplate.getForEntity(url, PlaceDto[].class);
        if (response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }
        return Collections.emptyList();
    }
}
