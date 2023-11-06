package com.example.lab3.datastore;

import com.example.lab3.beer.Beer;
import com.example.lab3.brewery.Brewery;
import com.example.lab3.student.Student;
import com.example.lab3.util.CloningUtility;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.java.Log;

import java.util.*;
import java.util.stream.Collectors;


@Log
@ApplicationScoped
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

    public synchronized void deleteStudent(Student value) throws IllegalArgumentException {
        students.removeIf(student -> student.getId().equals(value.getId()));
    }

    public synchronized List<Beer> findAllBeers() {
        return beers.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized Optional<Beer> findBeer(UUID id) {
        return beers.stream()
                .filter(beer -> beer.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createBeer(Beer value) throws IllegalArgumentException {
        if (beers.stream().anyMatch(beer -> beer.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The beer id \"%s\" is not unique".formatted(value.getId()));
        }
        beers.add(CloningUtility.clone(value));
    }

    public synchronized void updateBeer(Beer value) throws IllegalArgumentException {
        if (beers.removeIf(student -> student.getId().equals(value.getId()))) {
            beers.add(CloningUtility.clone(value));
        } else {
            throw new IllegalArgumentException("The beer with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    public synchronized void deleteBeer(Beer value) throws IllegalArgumentException {
        beers.removeIf(beer -> beer.getId().equals(value.getId()));
    }

    public synchronized List<Brewery> findAllBreweries() {
        return breweries.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized Optional<Brewery> findBrewery(UUID id) {
        return breweries.stream()
                .filter(brewery -> brewery.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createBrewery(Brewery value) throws IllegalArgumentException {
        if (breweries.stream().anyMatch(brewery -> brewery.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The brewery id \"%s\" is not unique".formatted(value.getId()));
        }
        breweries.add(CloningUtility.clone(value));
    }

    public synchronized void updateBrewery(Brewery value) throws IllegalArgumentException {
        if (breweries.removeIf(brewery -> brewery.getId().equals(value.getId()))) {
            breweries.add(CloningUtility.clone(value));
        } else {
            throw new IllegalArgumentException("The brewery with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    public synchronized void deleteBrewery(Brewery value) throws IllegalArgumentException {
        beers.removeIf(beer -> beer.getBrewery().getId().equals(value.getId()));
        breweries.removeIf(brewery -> brewery.getId().equals(value.getId()));
    }
}
