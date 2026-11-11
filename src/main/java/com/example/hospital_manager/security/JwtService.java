package com.example.hospital_manager.security;
import com.example.hospital_manager.model.AppUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private final Key key; private final long expirationMs;
    public JwtService(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.expiration-ms}") long expirationMs) { key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); this.expirationMs=expirationMs; }
    public String generateToken(AppUser user) { return Jwts.builder().setSubject(user.getUsername()).claim("role",user.getRole().name()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+expirationMs)).signWith(key,SignatureAlgorithm.HS256).compact(); }
    public String extractUsername(String token) { return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject(); }
    public boolean isValid(String token) { try { Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token); return true; } catch (JwtException | IllegalArgumentException e) { return false; } }
    public long getExpirationMs() { return expirationMs; }
}
