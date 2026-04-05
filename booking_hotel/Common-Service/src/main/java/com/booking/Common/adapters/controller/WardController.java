package com.booking.Common.adapters.controller;

import com.booking.Common.adapters.dto.Reponse.WardResponseDTO;
import com.booking.Common.adapters.dto.Request.WardRequestDTO;
import com.booking.Common.application.service.WardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/wards")
@RequiredArgsConstructor
public class WardController {
    private final WardService wardService;
    @GetMapping
    public ResponseEntity<List<WardResponseDTO>> getAll() {
        return ResponseEntity.ok(wardService.getAll());
    }
    @GetMapping("/province/{provinceId}/sorted")
    public ResponseEntity<List<WardResponseDTO>> getByProvinceSorted(@PathVariable UUID provinceId) {
        return ResponseEntity.ok(wardService.getByProvinceSorted(provinceId));
    } @PostMapping


    @GetMapping("/province/{provinceId}/search")
    public ResponseEntity<List<WardResponseDTO>> searchByNameInProvince(
            @PathVariable UUID provinceId,
            @RequestParam String name
    ) {
        return ResponseEntity.ok(wardService.searchByNameInProvince(provinceId, name));
    }
}
