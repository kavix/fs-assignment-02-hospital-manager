package com.example.hospital_manager.repository;

import com.example.hospital_manager.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByOrderByAppointmentDateTimeAsc();
    List<Appointment> findByDoctorOrderByAppointmentDateTimeAsc(AppUser doctor);
    boolean existsByPatient(Patient patient);
    boolean existsByDoctorAndAppointmentDateTimeAndStatus(AppUser doctor, java.time.LocalDateTime dateTime, AppointmentStatus status);
}
