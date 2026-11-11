package com.example.hospital_manager.security;
import com.example.hospital_manager.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

public final class ErrorResponseWriter {
    private ErrorResponseWriter() {}
    public static void write(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status); response.setContentType("application/json");
        new ObjectMapper().findAndRegisterModules().writeValue(response.getOutputStream(), new ErrorResponse(Instant.now(),status,status==401?"Unauthorized":"Forbidden",message,""));
    }
}
