package com.example.hospital_manager.controller;
import com.example.hospital_manager.dto.UserResponse;
import com.example.hospital_manager.service.AuthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api")
public class StaffController {
    private final AuthService auth; public StaffController(AuthService auth) { this.auth=auth; }
    @GetMapping("/users") @PreAuthorize("hasRole('ADMIN')") public List<UserResponse> users() { return auth.users(); }
    @GetMapping("/doctors") public List<UserResponse> doctors() { return auth.doctors(); }
}
