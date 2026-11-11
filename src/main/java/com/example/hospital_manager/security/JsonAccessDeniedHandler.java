package com.example.hospital_manager.security;
import jakarta.servlet.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Component
public class JsonAccessDeniedHandler implements AccessDeniedHandler {
    public void handle(HttpServletRequest r,HttpServletResponse s,AccessDeniedException e) throws IOException { ErrorResponseWriter.write(s,403,"Access is denied"); }
}
