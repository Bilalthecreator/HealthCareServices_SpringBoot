package com.healthcare.order_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDTO {
    private Long patientId;
    private String medicationName;
    private int quantity;
    private String status;
}