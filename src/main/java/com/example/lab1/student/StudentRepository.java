package com.example.lab1.student;

import com.example.lab1.datastore.DataStore;
import com.example.lab1.repository.Repository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class StudentRepository implements Repository<Student, UUID> {

    private DataStore store;

    @Inject
    public StudentRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Student> find(UUID id) {
        return store.findStudent(id);
    }

    @Override
    public List<Student> findAll() {
        return store.findAllStudents();
    }

    @Override
    public void create(Student entity) {
        store.createStudent(entity);
    }

    @Override
    public void delete(Student entity) {
    }

    @Override
    public void update(Student entity) {
    }
}
