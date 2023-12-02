package com.example.lab5.student;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class StudentService {
    private final StudentRepository repository;

    private final Pbkdf2PasswordHash passwordHash;

    @Inject
    public StudentService(StudentRepository repository, @SuppressWarnings("CdiInjectionPointsInspection") Pbkdf2PasswordHash passwordHash) {
        this.repository = repository;
        this.passwordHash = passwordHash;
    }

    @RolesAllowed(StudentRoles.ADMIN)
    public Optional<Student> find(UUID id) {
        return repository.find(id);
    }

    @RolesAllowed(StudentRoles.ADMIN)
    public Optional<Student> find(String login) {
        return repository.findByLogin(login);
    }

    @RolesAllowed(StudentRoles.ADMIN)
    public List<Student> findAll() {
        return repository.findAll();
    }

    @PermitAll
    public void create(Student entity) {
        entity.setPassword(passwordHash.generate(entity.getPassword().toCharArray()));
        repository.create(entity);
    }

    @PermitAll
    public boolean verify(String login, String password) {
        return find(login)
                .map(user -> passwordHash.verify(password.toCharArray(), user.getPassword()))
                .orElse(false);
    }

    public void update(Student entity) {
        repository.update(entity);
    }

    @RolesAllowed(StudentRoles.ADMIN)
    public void delete(UUID id) {
        var student = repository.find(id);
        if (student.isPresent()) {
            repository.delete(student.get());
        } else {
            throw new NotFoundException();
        }
    }

    public void putAvatar(UUID id, InputStream stream) {
        Path path = Paths.get(String.format("../avatar%s.png", id.toString()));
        try {
            Files.write(path, stream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<byte[]> getAvatar(UUID id) {
        Path path = Paths.get(String.format("../avatar%s.png", id.toString()));
        try {
            byte[] data = Files.readAllBytes(path);
            return Optional.of(data);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public void deleteAvatar(UUID id) {
        Path path = Paths.get(String.format("../avatar%s.png", id.toString()));
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
