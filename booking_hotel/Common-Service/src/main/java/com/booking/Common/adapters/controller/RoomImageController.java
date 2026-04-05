package com.booking.Common.adapters.controller;

import com.booking.Common.adapters.dto.Reponse.RoomImageResponseDTO;
import com.booking.Common.adapters.dto.Request.RoomImageRequestDTO;
import com.booking.Common.application.service.RoomImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/room-images")
@RequiredArgsConstructor
public class RoomImageController {
    private final RoomImageService roomImageService;

    // Lấy tất cả ảnh của một phòng
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<RoomImageResponseDTO>> getImagesByRoom(@PathVariable UUID roomId) {
        List<RoomImageResponseDTO> images = roomImageService.getImagesByRoom(roomId);
        return ResponseEntity.ok(images);
    }

    // Thêm ảnh
    @PostMapping
    public ResponseEntity<RoomImageResponseDTO> create(@RequestBody RoomImageRequestDTO request) {
        return ResponseEntity.ok(roomImageService.createRoomImage(request));
    }

    // Xóa ảnh
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        roomImageService.deleteRoomImage(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
