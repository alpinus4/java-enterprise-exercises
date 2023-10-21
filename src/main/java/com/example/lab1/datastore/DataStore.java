package com.example.lab1.datastore;

import com.example.lab1.beer.Beer;
import com.example.lab1.brewery.Brewery;
import com.example.lab1.student.Student;
import com.example.lab1.util.CloningUtility;

import java.util.*;
import java.util.stream.Collectors;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;


@Log
@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataStore {
    private final Set<Student> students = new HashSet<>();

    private final Set<Beer> beers = new HashSet<>();

    private final Set<Brewery> breweries = new HashSet<>();

    public synchronized List<Student> findAllStudents() {
        return students.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized Optional<Student> findStudent(UUID id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createStudent(Student value) throws IllegalArgumentException {
        if (students.stream().anyMatch(student -> student.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The user id \"%s\" is not unique".formatted(value.getId()));
        }
        students.add(CloningUtility.clone(value));
    }

    public synchronized void updateStudent(Student value) throws IllegalArgumentException {
        if (students.removeIf(student -> student.getId().equals(value.getId()))) {
            students.add(CloningUtility.clone(value));
        } else {
            throw new IllegalArgumentException("The user with id \"%s\" does not exist".formatted(value.getId()));
        }
    }
}
