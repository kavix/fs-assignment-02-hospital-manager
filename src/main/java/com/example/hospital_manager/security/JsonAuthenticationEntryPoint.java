package com.example.hospital_manager.security;
import jakarta.servlet.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Component
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {
    public void commence(HttpServletRequest r,HttpServletResponse s,AuthenticationException e) throws IOException { ErrorResponseWriter.write(s,401,"Authentication is required"); }
}
