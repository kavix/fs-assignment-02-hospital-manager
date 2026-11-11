package com.example.hospital_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne(optional = false) @JoinColumn(name = "doctor_id")
    private AppUser doctor;
    @NotNull @Column(nullable = false)
    private LocalDateTime appointmentDateTime;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;
    @Size(max = 500)
    private String reason;
}
