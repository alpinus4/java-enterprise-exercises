package com.example.lab5.student.model.function;

import com.example.lab5.student.Student;
import com.example.lab5.student.model.StudentEditModel;
import com.example.lab5.student.model.StudentModel;

import java.util.function.Function;

public class StudentToEditModelFunction implements Function<Student, StudentEditModel> {

    @Override
    public StudentEditModel apply(Student student) {
        return StudentEditModel.builder()
                .name(student.getName())
                .surname(student.getSurname())
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
