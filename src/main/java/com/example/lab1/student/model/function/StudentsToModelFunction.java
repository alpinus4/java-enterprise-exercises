package com.example.lab1.student.model.function;

import com.example.lab1.student.Student;
import com.example.lab1.student.model.StudentsModel;

import java.util.List;
import java.util.function.Function;

public class StudentsToModelFunction implements Function<List<Student>, StudentsModel> {
    @Override
    public StudentsModel apply(List<Student> entity) {
        return StudentsModel.builder()
                .students(entity.stream()
                        .map(student ->  StudentsModel.Student.builder()
                                .id(student.getId())
                                .name(student.getName())
                                .surname(student.getSurname())
                                .build())
                        .toList())
                .build();
    }
}
