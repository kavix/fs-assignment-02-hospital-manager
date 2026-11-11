package com.example.hospital_manager.security;
import com.example.hospital_manager.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository users;
    public CustomUserDetailsService(UserRepository users) { this.users=users; }
    public UserDetails loadUserByUsername(String username) { return users.findByUsername(username).map(u -> User.withUsername(u.getUsername()).password(u.getPassword()).roles(u.getRole().name()).build()).orElseThrow(() -> new UsernameNotFoundException("User not found")); }
}
