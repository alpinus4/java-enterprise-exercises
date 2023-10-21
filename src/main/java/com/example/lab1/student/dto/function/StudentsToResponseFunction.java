package com.example.lab1.student.dto.function;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.List;

import com.example.lab1.student.Student;
import com.example.lab1.student.dto.GetStudentsResponse;

public class StudentsToResponseFunction implements Function<List<Student>, GetStudentsResponse> {

    @Override
    public GetStudentsResponse apply(List<Student> entities) {
        return GetStudentsResponse.builder()
                .students(entities.stream().map(student -> GetStudentsResponse.Student.builder().id(student.getId()).name(student.getName()).build()).collect(Collectors.toList()))
                .build();
    }

}
