package com.example.hospital_manager.service;
import com.example.hospital_manager.dto.MedicalRecordRequest;
import com.example.hospital_manager.exception.ApiException;
import com.example.hospital_manager.model.*;
import com.example.hospital_manager.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordRepository records; private final PatientRepository patients; private final UserRepository users;
    public MedicalRecordService(MedicalRecordRepository r, PatientRepository p, UserRepository u) { records=r; patients=p; users=u; }
    public MedicalRecord create(MedicalRecordRequest request, String username) {
        Patient p=patients.findById(request.patientId()).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,"Patient not found"));
        AppUser d=users.findByUsername(username).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,"Doctor not found"));
        MedicalRecord r=new MedicalRecord(); r.setPatient(p); r.setDoctor(d); r.setDiagnosis(request.diagnosis()); r.setPrescription(request.prescription()); return records.save(r);
    }
    public List<MedicalRecord> list(Long patientId) { Patient p=patients.findById(patientId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,"Patient not found")); return records.findByPatientOrderByCreatedAtDesc(p); }
}
