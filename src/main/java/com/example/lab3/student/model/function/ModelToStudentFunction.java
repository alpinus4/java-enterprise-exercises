package com.example.lab3.student.model.function;

import com.example.lab3.beer.Beer;
import com.example.lab3.student.Student;
import com.example.lab3.student.model.StudentCreateModel;
import com.example.lab3.student.model.StudentModel;

import java.util.List;
import java.util.function.Function;

public class ModelToStudentFunction implements Function<StudentCreateModel, Student> {
    @Override
    public Student apply(StudentCreateModel studentModel) {
        return Student.builder()
                .id(studentModel.getId())
                .name(studentModel.getName())
                .surname(studentModel.getSurname())
                .birthDate(studentModel.getBirthDate())
                .drunkenBeers((List<Beer>) studentModel.getDrunkenBeers().stream()
                        .map(beer ->  Beer.builder()
                                .id(beer.getId())
                                .name(beer.getName())
                                .alcoholContent(beer.getAlcoholContent())
                                .build())
                        .toList())
                .build();
    }
}
