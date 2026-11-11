package com.example.hospital_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class MedicalRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne(optional = false) @JoinColumn(name = "doctor_id")
    private AppUser doctor;
    @NotBlank @Column(nullable = false, length = 2000)
    private String diagnosis;
    @NotBlank @Column(nullable = false, length = 2000)
    private String prescription;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
