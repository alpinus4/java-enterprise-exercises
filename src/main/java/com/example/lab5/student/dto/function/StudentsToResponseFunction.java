package com.example.lab5.student.dto.function;

import com.example.lab5.student.Student;
import com.example.lab5.student.dto.GetStudentsResponse;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StudentsToResponseFunction implements Function<List<Student>, GetStudentsResponse> {

    @Override
    public GetStudentsResponse apply(List<Student> entities) {
        return GetStudentsResponse.builder()
                .students(entities.stream().map(student -> GetStudentsResponse.Student.builder().id(student.getId()).name(student.getName()).build()).collect(Collectors.toList()))
                .build();
    }

}
