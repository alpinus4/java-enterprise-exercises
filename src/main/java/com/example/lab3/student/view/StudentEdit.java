package com.example.lab3.student.view;

import com.example.lab3.component.ModelFunctionFactory;
import com.example.lab3.student.Student;
import com.example.lab3.student.StudentService;
import com.example.lab3.student.model.StudentEditModel;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class StudentEdit implements Serializable {
    private final StudentService studentService;

    private final ModelFunctionFactory modelFunctionFactory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private StudentEditModel student;

    @Inject
    public StudentEdit(StudentService studentService, ModelFunctionFactory modelFunctionFactory) {
        this.studentService = studentService;
        this.modelFunctionFactory = modelFunctionFactory;
    }

    public void init() throws IOException {
        Optional<Student> student = studentService.find(id);
        if (student.isPresent()) {
            this.student = modelFunctionFactory.studentToEditModel().apply(student.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Student not found");
        }
    }

    public String saveAction() throws IOException {
        studentService.update(modelFunctionFactory.updateStudent().apply(studentService.find(id).orElseThrow(), student));
        if(student.getAvatar() != null) {
            studentService.putAvatar(id, student.getAvatar().getInputStream());
        }
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }
}
