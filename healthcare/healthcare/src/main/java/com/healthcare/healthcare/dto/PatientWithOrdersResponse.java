package com.healthcare.healthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PatientWithOrdersResponse {
    private Long id;
    private String name;
    private int age;
    private String disease;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long totalOrders;
    private List<OrderResponse> orders;
}