package com.example.hospital_manager.service;

import com.example.hospital_manager.dto.*;
import com.example.hospital_manager.exception.ApiException;
import com.example.hospital_manager.model.*;
import com.example.hospital_manager.repository.UserRepository;
import com.example.hospital_manager.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthService {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public AuthService(UserRepository users, PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.users = users; this.encoder = encoder; this.authenticationManager = authenticationManager; this.jwtService = jwtService;
    }
    public AuthResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        AppUser user = users.findByUsername(auth.getName()).orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));
        return new AuthResponse(jwtService.generateToken(user), "Bearer", jwtService.getExpirationMs(), user.getRole());
    }
    public UserResponse register(RegisterRequest request) {
        if (users.existsByUsername(request.username())) throw new ApiException(HttpStatus.CONFLICT, "Username already exists");
        return UserResponse.from(users.save(new AppUser(request.username(), request.name(), encoder.encode(request.password()), request.role())));
    }
    public UserResponse me(String username) { return UserResponse.from(find(username)); }
    public List<UserResponse> users() { return users.findAll().stream().map(UserResponse::from).toList(); }
    public List<UserResponse> doctors() { return users.findByRole(Role.DOCTOR).stream().map(UserResponse::from).toList(); }
    public AppUser find(String username) { return users.findByUsername(username).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found")); }
}
