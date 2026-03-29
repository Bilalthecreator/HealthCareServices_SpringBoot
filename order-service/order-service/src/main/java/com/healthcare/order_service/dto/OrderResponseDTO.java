package com.healthcare.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private Long patientId;
    private String medicationName;
    private int quantity;
    private String status;
    private LocalDateTime orderDate;
    private LocalDateTime updatedAt;
}