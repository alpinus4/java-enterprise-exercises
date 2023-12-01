package com.example.lab5.student.dto.function;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.BiFunction;

import com.example.lab5.student.Student;
import com.example.lab5.student.dto.PostStudentRequest;

public class RequestToStudentFunction implements BiFunction<UUID, PostStudentRequest, Student> {

    @Override
    public Student apply(UUID id, PostStudentRequest request) {
        return Student.builder()
                .id(id)
                .name(request.getName())
                .surname(request.getSurname())
                .birthDate(request.getBirthDate())
                .drunkenBeers(new ArrayList<>())
                .avatar(null)
                .build();
    }
    
}
