package com.booking.Common.adapters.controller;

import com.booking.Common.adapters.dto.Reponse.RoomServiceRequestDTO;
import com.booking.Common.adapters.dto.Request.RoomServiceResponseDTO;
import com.booking.Common.application.service.RoomServiceManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/room-services")
@RequiredArgsConstructor

public class RoomServiceController{
    private final RoomServiceManagement roomServiceManagement;

    @PostMapping
    public ResponseEntity<RoomServiceResponseDTO> create(@RequestBody RoomServiceRequestDTO request) {
        return ResponseEntity.ok(roomServiceManagement.createRoomService(request));
    }
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<RoomServiceResponseDTO>> getByRoom(@PathVariable UUID roomId) {
        return ResponseEntity.ok(roomServiceManagement.getServicesByRoom(roomId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomServiceResponseDTO>> getByHotel(@PathVariable UUID hotelId) {
        return ResponseEntity.ok(roomServiceManagement.getServicesByHotel(hotelId));
    }
}
