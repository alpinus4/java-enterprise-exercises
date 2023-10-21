package com.example.lab1.listener;

import com.example.lab1.component.DtoFunctionFactory;
import com.example.lab1.student.SimpleStudentController;
import com.example.lab1.student.StudentService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


public class CreateControllers implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        StudentService studentService = (StudentService) event.getServletContext().getAttribute("studentService");

        event.getServletContext().setAttribute("studentController", new SimpleStudentController(
                studentService,
                new DtoFunctionFactory()
        ));
    }
}
