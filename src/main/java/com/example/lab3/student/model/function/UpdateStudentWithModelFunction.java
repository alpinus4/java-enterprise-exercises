package com.example.lab3.student.model.function;

import com.example.lab3.beer.Beer;
import com.example.lab3.student.Student;
import com.example.lab3.student.model.StudentEditModel;
import com.example.lab3.student.model.StudentModel;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.io.Serializable;

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
