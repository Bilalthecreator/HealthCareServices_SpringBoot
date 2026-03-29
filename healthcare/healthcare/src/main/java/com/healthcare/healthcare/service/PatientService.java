package com.healthcare.healthcare.service;

import com.healthcare.healthcare.client.OrderClient;
import com.healthcare.healthcare.dto.OrderResponse;
import com.healthcare.healthcare.dto.PatientRequestDTO;
import com.healthcare.healthcare.dto.PatientResponseDTO;
import com.healthcare.healthcare.dto.PatientWithOrdersResponse;
import com.healthcare.healthcare.entity.Patient;
import com.healthcare.healthcare.exception.ResourceNotFoundException;
import com.healthcare.healthcare.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository repository;
    private final OrderClient orderClient;

    public PatientService(PatientRepository repository, OrderClient orderClient) {
        this.repository = repository;
        this.orderClient = orderClient;
    }

    private PatientResponseDTO toDTO(Patient patient) {
        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getDisease(),
                patient.getCreatedAt(),
                patient.getUpdatedAt()
        );
    }

    public List<PatientResponseDTO> getPatients() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PatientResponseDTO getPatientById(Long id) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        return toDTO(patient);
    }

    public PatientResponseDTO createPatient(PatientRequestDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Patient name cannot be empty");
        }

        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setDisease(dto.getDisease());
        patient.setCreatedAt(LocalDateTime.now());  // ✅ No more new Date()
        patient.setUpdatedAt(LocalDateTime.now());

        return toDTO(repository.save(patient));
    }

    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO dto) {
        Patient existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));

        existing.setName(dto.getName());
        existing.setAge(dto.getAge());
        existing.setDisease(dto.getDisease());
        existing.setUpdatedAt(LocalDateTime.now());  // ✅ No more new Date()

        return toDTO(repository.save(existing));
    }

    public void deletePatient(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public PatientWithOrdersResponse getPatientWithOrders(Long id) {
        Patient patient = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));

        List<OrderResponse> orders = orderClient.getOrdersByPatientId(id);
        Long totalOrders = orderClient.countOrdersByPatientId(id);

        return new PatientWithOrdersResponse(
            patient.getId(), patient.getName(), patient.getAge(),
            patient.getDisease(), patient.getCreatedAt(), patient.getUpdatedAt(),
            totalOrders, orders
        );
    }
}