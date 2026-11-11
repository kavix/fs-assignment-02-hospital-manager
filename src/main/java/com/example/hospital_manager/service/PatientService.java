package com.example.hospital_manager.service;
import com.example.hospital_manager.exception.ApiException;
import com.example.hospital_manager.model.Patient;
import com.example.hospital_manager.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patients; private final AppointmentRepository appointments; private final MedicalRecordRepository records;
    public PatientService(PatientRepository p, AppointmentRepository a, MedicalRecordRepository r) { patients=p; appointments=a; records=r; }
    public List<Patient> find(String name) { return name == null || name.isBlank() ? patients.findAll() : patients.findByNameContainingIgnoreCase(name); }
    public Patient get(Long id) { return patients.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Patient not found")); }
    public Patient save(Patient patient) { return patients.save(patient); }
    public Patient update(Long id, Patient value) { Patient p=get(id); p.setName(value.getName()); p.setDateOfBirth(value.getDateOfBirth()); p.setPhone(value.getPhone()); p.setEmail(value.getEmail()); p.setAddress(value.getAddress()); return patients.save(p); }
    public void delete(Long id) { Patient p=get(id); if (appointments.existsByPatient(p) || records.existsByPatient(p)) throw new ApiException(HttpStatus.CONFLICT, "Cannot delete a patient with appointments or medical records"); patients.delete(p); }
}
