package com.example.lab1.component;

import com.example.lab1.student.model.function.StudentsToModelFunction;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ModelFunctionFactory {
    public StudentsToModelFunction studentsToModel() {
        return new StudentsToModelFunction();
    }
}
