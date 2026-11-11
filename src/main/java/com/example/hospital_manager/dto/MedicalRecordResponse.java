package com.example.hospital_manager.dto;
import com.example.hospital_manager.model.*;
import java.time.LocalDateTime;
public record MedicalRecordResponse(Long id, Long patientId, String patientName, Long doctorId, String doctorUsername, String diagnosis, String prescription, LocalDateTime createdAt) {
    public static MedicalRecordResponse from(MedicalRecord r) { return new MedicalRecordResponse(r.getId(), r.getPatient().getId(), r.getPatient().getName(), r.getDoctor().getId(), r.getDoctor().getUsername(), r.getDiagnosis(), r.getPrescription(), r.getCreatedAt()); }
}
