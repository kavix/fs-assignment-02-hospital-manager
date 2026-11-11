package com.example.hospital_manager.controller;
import com.example.hospital_manager.dto.*;
import com.example.hospital_manager.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/records")
public class MedicalRecordController {
    private final MedicalRecordService service; public MedicalRecordController(MedicalRecordService service) { this.service=service; }
    @PostMapping @PreAuthorize("hasRole('DOCTOR')") public MedicalRecordResponse create(@Valid @RequestBody MedicalRecordRequest r,Authentication a) { return MedicalRecordResponse.from(service.create(r,a.getName())); }
    @GetMapping("/patient/{patientId}") public List<MedicalRecordResponse> list(@PathVariable Long patientId) { return service.list(patientId).stream().map(MedicalRecordResponse::from).toList(); }
}
