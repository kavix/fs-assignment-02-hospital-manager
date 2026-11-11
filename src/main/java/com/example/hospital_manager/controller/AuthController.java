package com.example.hospital_manager.controller;
import com.example.hospital_manager.dto.*;
import com.example.hospital_manager.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/auth")
public class AuthController {
    private final AuthService auth;
    public AuthController(AuthService auth) { this.auth=auth; }
    @PostMapping("/login") public AuthResponse login(@Valid @RequestBody LoginRequest r) { return auth.login(r); }
    @PostMapping("/register") public UserResponse register(@Valid @RequestBody RegisterRequest r) { return auth.register(r); }
    @GetMapping("/me") public UserResponse me(Authentication a) { return auth.me(a.getName()); }
}
