package com.example.lab1.student;

import com.example.lab1.component.DtoFunctionFactory;
import com.example.lab1.controller.exception.NotFoundException;
import com.example.lab1.student.dto.GetStudentResponse;
import com.example.lab1.student.dto.GetStudentsResponse;

import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

public class SimpleStudentController implements StudentController{

    private final StudentService service;

    private final DtoFunctionFactory factory;

    public SimpleStudentController(StudentService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetStudentsResponse getStudents() {
        return factory.studentsToResponse().apply(service.findAll());
    }

    @Override
    public GetStudentResponse getStudent(UUID id) {
        return factory.studentToResponse().apply(service.find(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public void deleteStudentAvatar(UUID id) {
        service.find(id).ifPresentOrElse(
                student -> service.deleteAvatar(id),
                () -> {
                    throw new NotFoundException("Cannot find user");
                }
        );
    }

    @Override
    public byte[] getStudentAvatar(UUID id) {
        Optional<byte[]> avatar = service.getAvatar(id);
        if (service.find(id).isPresent() && avatar.isPresent()) {
            return avatar.get();
        }
        throw new NotFoundException("Cannot find user");
    }

    @Override
    public void putStudentAvatar(UUID id, InputStream avatar) {
        service.find(id).ifPresentOrElse(
                student -> service.putAvatar(id, avatar),
                () -> {
                    throw new NotFoundException("Cannot find user");
                }
        );
    }
}
