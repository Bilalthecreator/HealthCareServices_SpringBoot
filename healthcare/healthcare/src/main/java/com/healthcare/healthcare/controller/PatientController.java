package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.PatientRequestDTO;
import com.healthcare.healthcare.dto.PatientResponseDTO;
import com.healthcare.healthcare.dto.PatientWithOrdersResponse;
import com.healthcare.healthcare.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        return ResponseEntity.ok(service.getPatients());         // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPatientById(id));   // 200 OK
    }

    @GetMapping("/{id}/orders")
public ResponseEntity<PatientWithOrdersResponse> getPatientWithOrders(@PathVariable Long id) {
    return ResponseEntity.ok(service.getPatientWithOrders(id));
}

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@RequestBody PatientRequestDTO dto) {
        PatientResponseDTO created = service.createPatient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable Long id,
            @RequestBody PatientRequestDTO dto) {
        return ResponseEntity.ok(service.updatePatient(id, dto));       // 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        service.deletePatient(id);
        return ResponseEntity.noContent().build();                      // 204 No Content
    }
}