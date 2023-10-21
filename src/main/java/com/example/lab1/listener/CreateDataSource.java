package com.example.lab1.listener;

import com.example.lab1.datastore.DataStore;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class CreateDataSource implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute("datasource", new DataStore());
    }
}
