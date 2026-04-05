package com.booking.Common.adapters.controller;

import com.booking.Common.adapters.dto.Reponse.HotelImageResponseDTO;
import com.booking.Common.adapters.dto.Request.HotelImageRequestDTO;
import com.booking.Common.application.service.HotelImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/hotel-images")
@RequiredArgsConstructor
public class HotelImageController {
    private final HotelImageService hotelImageService;
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<HotelImageResponseDTO>> getImagesByHotel(@PathVariable UUID hotelId) {
        return ResponseEntity.ok(hotelImageService.getImagesByHotel(hotelId));
    }
    // Thêm ảnh
    @PostMapping
    public ResponseEntity<HotelImageResponseDTO> create(@RequestBody HotelImageRequestDTO request) {
        return ResponseEntity.ok(hotelImageService.createHotelImage(request));
    }

    // Xóa ảnh
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        hotelImageService.deleteHotelImage(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
