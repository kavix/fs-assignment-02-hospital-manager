package com.example.hospital_manager.exception;
import com.example.hospital_manager.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ErrorResponse error(HttpStatus s,String m,HttpServletRequest r) { return new ErrorResponse(Instant.now(),s.value(),s.getReasonPhrase(),m,r.getRequestURI()); }
    @ExceptionHandler(ApiException.class) ResponseEntity<ErrorResponse> api(ApiException e,HttpServletRequest r) { return ResponseEntity.status(e.getStatus()).body(error(e.getStatus(),e.getMessage(),r)); }
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException e,HttpServletRequest r) { String m=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).collect(Collectors.joining(", ")); return ResponseEntity.badRequest().body(error(HttpStatus.BAD_REQUEST,m,r)); }
    @ExceptionHandler({BadCredentialsException.class, AuthenticationException.class}) ResponseEntity<ErrorResponse> auth(Exception e,HttpServletRequest r) { return ResponseEntity.status(401).body(error(HttpStatus.UNAUTHORIZED,"Invalid credentials",r)); }
}
