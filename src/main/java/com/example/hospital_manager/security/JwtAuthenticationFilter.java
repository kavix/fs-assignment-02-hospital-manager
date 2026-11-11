package com.example.hospital_manager.security;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwt; private final CustomUserDetailsService users;
    public JwtAuthenticationFilter(JwtService jwt, CustomUserDetailsService users) { this.jwt=jwt; this.users=users; }
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws ServletException,IOException {
        String header=request.getHeader("Authorization");
        if(header!=null && header.startsWith("Bearer ")) { String token=header.substring(7); if(jwt.isValid(token)) { String name=jwt.extractUsername(token); UserDetails details=users.loadUserByUsername(name); var auth=new UsernamePasswordAuthenticationToken(details,null,details.getAuthorities()); auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); SecurityContextHolder.getContext().setAuthentication(auth); } }
        chain.doFilter(request,response);
    }
}
