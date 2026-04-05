package com.booking.Booking.application.service;

import com.booking.Booking.adapters.dto.HotelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceClient {
    private final RestTemplate restTemplate;

    @Value("${services.common.url}")
    private String commonServiceUrl;

    public HotelDTO getHotelById(UUID hotelId) {
        try {
            return restTemplate.getForObject(
                    commonServiceUrl + "/api/hotels/" + hotelId,
                    HotelDTO.class
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Hotel not found");
        } catch (Exception e) {
            throw new RuntimeException("Common service unavailable");
        }
    }

    public String getHotelNameById(UUID hotelId) {
        return getHotelById(hotelId).getName();
    }

    public void validateHotel(UUID hotelId) {
        getHotelById(hotelId); // sẽ throw exception nếu không tìm thấy
    }
}
