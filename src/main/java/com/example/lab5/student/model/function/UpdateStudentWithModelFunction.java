package com.example.lab5.student.model.function;

import com.example.lab5.student.Student;
import com.example.lab5.student.model.StudentEditModel;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.BiFunction;

public class UpdateStudentWithModelFunction implements BiFunction<Student, StudentEditModel, Student>, Serializable {
    @Override
    @SneakyThrows
    public Student apply(Student student, StudentEditModel request) {
        return Student.builder()
                .id(student.getId())
                .name(request.getName())
                .surname(request.getSurname())
                .birthDate(request.getBirthDate())
                .drunkenBeers(new ArrayList<>())
                .avatar(request.getAvatar() != null
                        ? request.getAvatar().getInputStream().readAllBytes()
                        : student.getAvatar())
                .build();
    }
}
