package com.healthcare.healthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PatientResponseDTO {
    private Long id;
    private String name;
    private int age;
    private String disease;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}