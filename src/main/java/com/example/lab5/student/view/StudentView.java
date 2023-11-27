package com.example.lab5.student.view;

import com.example.lab5.component.ModelFunctionFactory;
import com.example.lab5.student.Student;
import com.example.lab5.student.StudentService;
import com.example.lab5.student.model.StudentModel;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class StudentView implements Serializable {
    private final StudentService studentService;

    private final ModelFunctionFactory modelFunctionFactory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private StudentModel studentModel;


    @Inject
    public StudentView(StudentService studentService, ModelFunctionFactory modelFunctionFactory) {
        this.studentService = studentService;
        this.modelFunctionFactory = modelFunctionFactory;
    }

    public void init() throws IOException {
        Optional<Student> student = studentService.find(id);
        if (student.isPresent()) {
            studentModel = modelFunctionFactory.studentToModel().apply(student.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Student not found");
        }
    }
}
