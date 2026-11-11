package com.example.hospital_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max = 100) @Column(nullable = false)
    private String name;
    @NotNull @Past @Column(nullable = false)
    private LocalDate dateOfBirth;
    @NotBlank @Size(max = 30) @Column(nullable = false)
    private String phone;
    @Email @Size(max = 150)
    private String email;
    @Size(max = 500)
    private String address;
}
