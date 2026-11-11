package com.example.hospital_manager.config;
import com.example.hospital_manager.model.AppUser;
import com.example.hospital_manager.model.Patient;
import com.example.hospital_manager.model.Role;
import com.example.hospital_manager.repository.PatientRepository;
import com.example.hospital_manager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;

@Configuration
public class DataLoader {
    @Bean CommandLineRunner seed(UserRepository users, PatientRepository patients, PasswordEncoder encoder) {
        return args -> {
            if (users.count() == 0) {
                users.save(new AppUser("admin",encoder.encode("Admin123!"),Role.ADMIN));
                users.save(new AppUser("doctor",encoder.encode("Doctor123!"),Role.DOCTOR));
                users.save(new AppUser("nurse",encoder.encode("Nurse123!"),Role.NURSE));
                users.save(new AppUser("receptionist",encoder.encode("Reception123!"),Role.RECEPTIONIST));
                users.save(new AppUser("patient",encoder.encode("Patient123!"),Role.PATIENT));
            }
            if (patients.count() == 0) {
                Patient p=new Patient();
                p.setName("Kasun Fernando");
                p.setDateOfBirth(LocalDate.of(1990, 1, 1));
                p.setPhone("+94 71 234 5678");
                p.setEmail("kasun.fernando@example.lk");
                p.setAddress("No. 12, Kandy Road, Kurunegala, Sri Lanka");
                patients.save(p);
                Patient second=new Patient();
                second.setName("Nimal Perera");
                second.setDateOfBirth(LocalDate.of(1985, 6, 15));
                second.setPhone("+94 77 123 4567");
                second.setEmail("nimal.perera@example.lk");
                second.setAddress("No. 25, Galle Road, Colombo 03, Sri Lanka");
                patients.save(second);
            }
        };
    }
}
