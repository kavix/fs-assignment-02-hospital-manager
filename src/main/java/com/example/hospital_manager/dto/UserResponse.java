package com.example.hospital_manager.dto;
import com.example.hospital_manager.model.*;
public record UserResponse(Long id, String username, String name, Role role) {
    public static UserResponse from(AppUser u) {
        return new UserResponse(u.getId(), u.getUsername(), u.getName(), u.getRole());
    }
}
