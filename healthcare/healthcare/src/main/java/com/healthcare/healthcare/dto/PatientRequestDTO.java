package com.healthcare.healthcare.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequestDTO {
    private String name;
    private int age;
    private String disease;
}