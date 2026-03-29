package com.healthcare.healthcare.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private Long patientId;
    private String medicationName;
    private int quantity;
    private String status;
    private LocalDateTime orderDate;
    private LocalDateTime updatedAt;
}