package com.example.hospital_manager.service;
import com.example.hospital_manager.dto.AppointmentRequest;
import com.example.hospital_manager.exception.ApiException;
import com.example.hospital_manager.model.*;
import com.example.hospital_manager.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointments; private final PatientRepository patients; private final UserRepository users;
    public AppointmentService(AppointmentRepository a, PatientRepository p, UserRepository u) { appointments=a; patients=p; users=u; }
    public Appointment book(AppointmentRequest r) {
        Patient p=patients.findById(r.patientId()).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,"Patient not found"));
        AppUser d=users.findById(r.doctorId()).filter(u -> u.getRole()==Role.DOCTOR).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,"Doctor not found"));
        if (appointments.existsByDoctorAndAppointmentDateTimeAndStatus(d,r.appointmentDateTime(),AppointmentStatus.SCHEDULED)) throw new ApiException(HttpStatus.CONFLICT,"Doctor is already booked");
        Appointment a=new Appointment(); a.setPatient(p); a.setDoctor(d); a.setAppointmentDateTime(r.appointmentDateTime()); a.setReason(r.reason()); return appointments.save(a);
    }
    public List<Appointment> list(String username, boolean doctorOnly) { return doctorOnly ? appointments.findByDoctorOrderByAppointmentDateTimeAsc(users.findByUsername(username).orElseThrow()) : appointments.findAllByOrderByAppointmentDateTimeAsc(); }
    public Appointment get(Long id) { return appointments.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,"Appointment not found")); }
    public Appointment status(Long id, AppointmentStatus status, String username, boolean enforceOwner) { Appointment a=get(id); if(enforceOwner && !a.getDoctor().getUsername().equals(username)) throw new ApiException(HttpStatus.FORBIDDEN,"Only the assigned doctor may update this appointment"); a.setStatus(status); return appointments.save(a); }
}
