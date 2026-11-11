package com.example.hospital_manager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_users")
@Getter @Setter @NoArgsConstructor
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 100)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private Role role;

    public AppUser(String username, String password, Role role) {
        this.username = username; this.password = password; this.role = role;
    }
}
