package com.example.lab3.student.model.function;

import com.example.lab3.student.Student;
import com.example.lab3.student.model.StudentModel;

import java.util.function.Function;

public class StudentToModelFunction implements Function<Student, StudentModel> {
    @Override
    public StudentModel apply(Student student) {
        return StudentModel.builder()
                .id(student.getId())
                .name(student.getName() + " " + student.getSurname())
                .birthDate(student.getBirthDate())
                .drunkenBeers(student.getDrunkenBeers().stream()
                        .map(beer ->  StudentModel.Beer.builder()
                                .id(beer.getId())
                                .name(beer.getName())
                                .alcoholContent(beer.getAlcoholContent())
                                .build())
                        .toList())
                .build();
    }
}
