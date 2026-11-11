package com.example.hospital_manager.controller;
import com.example.hospital_manager.dto.*;
import com.example.hospital_manager.model.*;
import com.example.hospital_manager.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService service; public AppointmentController(AppointmentService service) { this.service=service; }
    @PostMapping @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST')") public AppointmentResponse book(@Valid @RequestBody AppointmentRequest r) { return AppointmentResponse.from(service.book(r)); }
    @GetMapping public List<AppointmentResponse> list(Authentication a) { boolean doctor=a.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_DOCTOR")); return service.list(a.getName(),doctor).stream().map(AppointmentResponse::from).toList(); }
    @PatchMapping("/{id}/status") @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')") public AppointmentResponse status(@PathVariable Long id,@Valid @RequestBody StatusRequest r,Authentication a) { boolean doctor=a.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_DOCTOR")); return AppointmentResponse.from(service.status(id,r.status(),a.getName(),doctor)); }
}
