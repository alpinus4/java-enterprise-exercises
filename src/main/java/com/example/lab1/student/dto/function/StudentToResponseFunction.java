package com.example.lab1.student.dto.function;

import com.example.lab1.student.dto.GetStudentResponse;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.lab1.student.Student;

public class StudentToResponseFunction implements Function<Student, GetStudentResponse> {

    @Override
    public GetStudentResponse apply(Student entity) {
        return GetStudentResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .birthDate(entity.getBirthDate())
                .drunkenBeers(entity.getDrunkenBeers().stream().map(beer -> GetStudentResponse.Beer.builder().id(beer.getId()).name(beer.getName()).build()).collect(Collectors.toList()))
                .build();
    }

}

