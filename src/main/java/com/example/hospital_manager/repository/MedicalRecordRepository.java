package com.example.hospital_manager.repository;

import com.example.hospital_manager.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatientOrderByCreatedAtDesc(Patient patient);
    boolean existsByPatient(Patient patient);
}
