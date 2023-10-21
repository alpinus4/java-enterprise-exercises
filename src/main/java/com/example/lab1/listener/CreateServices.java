package com.example.lab1.listener;

import com.example.lab1.datastore.DataStore;
import com.example.lab1.student.StudentRepository;
import com.example.lab1.student.StudentService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class CreateServices implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        DataStore dataSource = (DataStore) event.getServletContext().getAttribute("datasource");

        StudentRepository userRepository = new StudentRepository(dataSource);

        event.getServletContext().setAttribute("studentService", new StudentService(userRepository));
    }

}
