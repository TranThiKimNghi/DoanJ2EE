package com.booking.Booking.application.service;

import com.booking.Booking.adapters.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceClient {

    private final RestTemplate restTemplate;

    @Value("${services.common.url}")
    private String commonServiceUrl;

    private String roomUrl(UUID roomId) {
        return commonServiceUrl + "/api/rooms/" + roomId;
    }

    // Lấy full RoomDTO
    public RoomDTO getRoomById(UUID roomId) {
        try {
            return restTemplate.getForObject(roomUrl(roomId), RoomDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Room not found: " + roomId);
        } catch (Exception e) {
            throw new RuntimeException("Common service unavailable");
        }
    }

    // Lấy số phòng nhanh gọn
    public String getRoomNumberById(UUID roomId) {
        return getRoomById(roomId).getRoomNumber();
    }

    // Chỉ validate sự tồn tại phòng
    public void validateRoom(UUID roomId) {
        getRoomById(roomId); // sẽ throw exception nếu không tìm thấy
    }

    // Cập nhật trạng thái phòng
    public void updateRoomStatus(UUID roomId, String status) {
        try {
            restTemplate.put(roomUrl(roomId) + "/status?status=" + status, null);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Room not found: " + roomId);
        } catch (Exception e) {
            throw new RuntimeException("Cannot update room status");
        }
    }
}
