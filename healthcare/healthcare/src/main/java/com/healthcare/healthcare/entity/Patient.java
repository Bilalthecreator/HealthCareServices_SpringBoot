package com.healthcare.healthcare.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String disease;

    private LocalDateTime createdAt;   // No @Temporal needed — Hibernate 6+ handles this natively
    private LocalDateTime updatedAt;
}