package com.example.lab1.student;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class StudentService {
    private final StudentRepository repository;

    @Inject
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Optional<Student> find(UUID id) {
        return repository.find(id);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public void create(Student entity) {
        repository.create(entity);
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