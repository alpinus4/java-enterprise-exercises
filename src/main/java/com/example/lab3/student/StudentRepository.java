package com.example.lab3.student;

import com.example.lab3.datastore.DataStore;
import com.example.lab3.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
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
        store.deleteStudent(entity);
    }

    @Override
    public void update(Student entity) {
        store.updateStudent(entity);
    }
}
