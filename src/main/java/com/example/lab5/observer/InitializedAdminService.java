package com.example.lab5.observer;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.lab5.student.Student;
import com.example.lab5.student.StudentRepository;
import com.example.lab5.student.StudentRoles;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@NoArgsConstructor(force = true)
public class InitializedAdminService {

    private final StudentRepository studentRepository;

    private final Pbkdf2PasswordHash passwordHash;

    @Inject
    public InitializedAdminService(
            StudentRepository studentRepository,
            @SuppressWarnings("CdiInjectionPointsInspection") Pbkdf2PasswordHash passwordHash
    ) {
        this.studentRepository = studentRepository;
        this.passwordHash = passwordHash;
    }

    @PostConstruct
    @SneakyThrows
    private void init() {
        if (studentRepository.findByLogin("admin-service").isEmpty()) {

            Student admin = Student.builder()
            .id(UUID.fromString("8f90180e-cd3c-4adc-8b6b-bd4245ac88f6"))
            .name("")
            .surname("")
            .birthDate(LocalDate.of(1999, 9, 9))
            .login("admin-service")
            .password(passwordHash.generate("admin".toCharArray()))
            .roles(List.of(StudentRoles.ADMIN, StudentRoles.USER))
            .build();

            studentRepository.create(admin);
        }
    }
    
}
