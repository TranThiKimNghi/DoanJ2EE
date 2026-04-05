package com.booking.Common.adapters.controller;

import com.booking.Common.adapters.dto.Reponse.ProvinceResponseDTO;
import com.booking.Common.adapters.dto.Request.ProvinceRequestDTO;
import com.booking.Common.application.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/provinces")
@RequiredArgsConstructor
public class ProvinceController {
    private final ProvinceService provinceService;

    @PostMapping
    public ResponseEntity<ProvinceResponseDTO> create(@RequestBody ProvinceRequestDTO request) {
        return ResponseEntity.ok(provinceService.createProvince(request));
    }

    @GetMapping
    public ResponseEntity<List<ProvinceResponseDTO>> getAll() {
        return ResponseEntity.ok(provinceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinceResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(provinceService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProvinceResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody ProvinceRequestDTO request
    ) {
        return ResponseEntity.ok(provinceService.updateProvince(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        provinceService.deleteProvince(id);
        return ResponseEntity.ok().build();
    }
}
