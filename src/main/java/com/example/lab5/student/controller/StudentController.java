package com.example.lab5.student.controller;

import com.example.lab5.student.dto.GetStudentResponse;
import com.example.lab5.student.dto.GetStudentsResponse;
import com.example.lab5.student.dto.PostStudentRequest;

import java.io.InputStream;
import java.util.UUID;

public interface StudentController {

    GetStudentsResponse getStudents();

    GetStudentResponse getStudent(UUID id);

    void postStudent(PostStudentRequest request);

    void deleteStudentAvatar(UUID id);

    byte[] getStudentAvatar(UUID id);

    void putStudentAvatar(UUID id, InputStream avatar);

}
