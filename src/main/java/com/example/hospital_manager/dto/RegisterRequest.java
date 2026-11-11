package com.example.hospital_manager.dto;
import com.example.hospital_manager.model.Role;
import jakarta.validation.constraints.*;
public record RegisterRequest(@NotBlank @Size(max=100) String username, @NotBlank @Size(min=8) String password, @NotNull Role role) {}
