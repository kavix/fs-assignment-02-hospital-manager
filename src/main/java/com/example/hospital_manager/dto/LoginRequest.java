package com.example.hospital_manager.dto;
import jakarta.validation.constraints.*;
public record LoginRequest(@NotBlank String username, @NotBlank String password) {}
