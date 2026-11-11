package com.example.hospital_manager.dto;
import jakarta.validation.constraints.*;
public record MedicalRecordRequest(@NotNull Long patientId, @NotBlank String diagnosis, @NotBlank String prescription) {}
