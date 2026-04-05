package com.booking.Common.adapters.controller;

import com.booking.Common.adapters.dto.Reponse.RoomResponseDTO;
import com.booking.Common.adapters.dto.Request.RoomRequestDTO;
import com.booking.Common.application.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor

public class RoomController {
    private final RoomService roomService;
    @PostMapping
    public ResponseEntity<RoomResponseDTO> create(@RequestBody RoomRequestDTO request) {
        return ResponseEntity.ok(roomService.createRoom(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> getAll() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomResponseDTO>> getByHotel(@PathVariable UUID hotelId) {
        return ResponseEntity.ok(roomService.getRoomsByHotel(hotelId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> update(@PathVariable UUID id, @RequestBody RoomRequestDTO request) {
        return ResponseEntity.ok(roomService.updateRoom(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
