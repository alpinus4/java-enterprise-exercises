package com.example.lab1.component;

import com.example.lab1.student.dto.function.StudentToResponseFunction;
import com.example.lab1.student.dto.function.StudentsToResponseFunction;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DtoFunctionFactory {

    public StudentToResponseFunction studentToResponse(){
        return new StudentToResponseFunction();
    }

    public StudentsToResponseFunction studentsToResponse(){
        return new StudentsToResponseFunction();
    }
}
