package com.booking.Common.adapters.controller;

import com.booking.Common.adapters.dto.HotelUpdateDTO;
import com.booking.Common.adapters.dto.Reponse.HotelResponseDTO;
import com.booking.Common.adapters.dto.Request.HotelRequestDTO;
import com.booking.Common.application.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelResponseDTO> createHotel(@RequestBody HotelRequestDTO request) {
        return ResponseEntity.ok(hotelService.createHotel(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> getHotelById(@PathVariable UUID id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @GetMapping("/province/{provinceId}")
    public ResponseEntity<List<HotelResponseDTO>> getByProvince(@PathVariable UUID provinceId) {
        return ResponseEntity.ok(hotelService.getByProvince(provinceId));
    }

    @GetMapping("/ward/{wardId}")
    public ResponseEntity<List<HotelResponseDTO>> getByWard(@PathVariable UUID wardId) {
        return ResponseEntity.ok(hotelService.getByWard(wardId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<HotelResponseDTO>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(hotelService.searchByName(name));
    }
    @GetMapping
    public ResponseEntity<List<HotelResponseDTO>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> updateHotel(
            @PathVariable UUID id,
            @RequestBody HotelUpdateDTO updateDTO
    ) {
        return ResponseEntity.ok(hotelService.updateHotel(id, updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable UUID id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok("Delete success");
    }

}
