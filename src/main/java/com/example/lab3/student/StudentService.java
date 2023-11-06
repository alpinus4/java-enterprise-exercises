package com.example.lab3.student;

import com.example.lab3.controller.exception.NotFoundException;
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

    public void update(Student entity) {
        repository.update(entity);
    }

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
