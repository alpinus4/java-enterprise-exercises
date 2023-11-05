package com.example.lab1.student.view;

import com.example.lab1.component.ModelFunctionFactory;
import com.example.lab1.student.StudentService;
import com.example.lab1.student.model.StudentsModel;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class StudentList {
    private final StudentService studentService;

    private StudentsModel students;

    private final ModelFunctionFactory modelFunctionFactory;

    @Inject
    public StudentList(StudentService studentService, ModelFunctionFactory modelFunctionFactory) {
        this.studentService = studentService;
        this.modelFunctionFactory = modelFunctionFactory;
    }

    public StudentsModel getStudents() {
        if (students == null) {
            students = modelFunctionFactory.studentsToModel().apply(studentService.findAll());
        }

        return students;
    }

    public String deleteAction(StudentsModel.Student student) {
        studentService.delete(student.getId());
        return "student_list?faces-redirect=true";
    }
}
