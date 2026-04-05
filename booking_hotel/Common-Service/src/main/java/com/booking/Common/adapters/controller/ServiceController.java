package com.booking.Common.adapters.controller;

import com.booking.Common.adapters.dto.Reponse.ServiceResponseDTO;
import com.booking.Common.adapters.dto.Request.ServiceRequestDTO;
import com.booking.Common.application.service.ServiceManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceManagement serviceManagement;

    @PostMapping
    public ResponseEntity<ServiceResponseDTO> create(@RequestBody ServiceRequestDTO request) {
        return ResponseEntity.ok(serviceManagement.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(serviceManagement.getById(id));
    }
    @GetMapping
    public ResponseEntity<List<ServiceResponseDTO>> getAllServices() {
        return ResponseEntity.ok(serviceManagement.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody ServiceRequestDTO request
    ) {
        return ResponseEntity.ok(serviceManagement.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        serviceManagement.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
