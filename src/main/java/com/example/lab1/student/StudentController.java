package com.example.lab1.student;

import com.example.lab1.student.dto.GetStudentResponse;
import com.example.lab1.student.dto.GetStudentsResponse;

import java.io.InputStream;
import java.util.UUID;

public interface StudentController {

    GetStudentsResponse getStudents();

    GetStudentResponse getStudent(UUID id);

    void deleteStudentAvatar(UUID id);

    byte[] getStudentAvatar(UUID id);

    void putStudentAvatar(UUID id, InputStream avatar);

}
