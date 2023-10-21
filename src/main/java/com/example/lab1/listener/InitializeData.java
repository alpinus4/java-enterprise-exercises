package com.example.lab1.listener;

import com.example.lab1.beer.Beer;
import com.example.lab1.brewery.Brewery;
import com.example.lab1.student.Student;
import com.example.lab1.student.StudentService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@WebListener
public class InitializeData implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        StudentService studentService = (StudentService) event.getServletContext().getAttribute("studentService");

        Brewery browary_lubelskie_sa = Brewery.builder().id(UUID.randomUUID()).establishmentDate(LocalDate.of(1846, 1, 1)).name("Perla browary lubelskie SA").location("Lublin").build();

        Beer perla_miodowa = Beer.builder().id(UUID.randomUUID()).name("Perla miodowa").brewery(browary_lubelskie_sa).type(Beer.Type.LIGHT).brewingDate(LocalDate.of(2023, 9, 9)).alcoholContent(6).build();

        Beer perla_chmielowa = Beer.builder().id(UUID.randomUUID()).name("Perla chmielowa").brewery(browary_lubelskie_sa).type(Beer.Type.LIGHT).brewingDate(LocalDate.of(2023, 9, 10)).alcoholContent(6).build();

        Beer perla_export = Beer.builder().id(UUID.randomUUID()).name("Perla Export").brewery(browary_lubelskie_sa).type(Beer.Type.LIGHT).brewingDate(LocalDate.of(2023, 9, 10)).alcoholContent(5.2F).build();

        Beer perla_mocna = Beer.builder().id(UUID.randomUUID()).name("Perla Mocna").brewery(browary_lubelskie_sa).type(Beer.Type.LIGHT).brewingDate(LocalDate.of(2023, 9, 10)).alcoholContent(7.1F).build();

        Beer perla_bezalkoholowa = Beer.builder().id(UUID.randomUUID()).name("Perla Bezalkoholowa").brewery(browary_lubelskie_sa).type(Beer.Type.LIGHT).brewingDate(LocalDate.of(2023, 9, 10)).alcoholContent(0F).build();

        Student janek = Student.builder().id(UUID.fromString("a4727e51-37fd-48a9-89c4-c71fcad03f66")).name("Jan").surname("Piwny").birthDate(LocalDate.of(1997, 10, 21)).drunkenBeers(List.of(perla_miodowa)).build();

        Student kuba = Student.builder().id(UUID.fromString("4cb14aab-1cf6-44c9-8ee8-dfd96576c5e2")).name("Kuba").surname("Mocny").birthDate(LocalDate.of(1998, 11, 11)).drunkenBeers(List.of(perla_mocna, perla_export, perla_miodowa, perla_chmielowa)).build();

        Student asia = Student.builder().id(UUID.fromString("2fabeeb8-30d9-40e8-b447-b266438f2329")).name("Joanna").surname("Kowalska").birthDate(LocalDate.of(1999, 9, 9)).drunkenBeers(List.of(perla_bezalkoholowa)).build();

        Student ania = Student.builder().id(UUID.fromString("26ce434b-1201-4564-a3a1-a4089eebf000")).name("Anna").surname("Kowalska").birthDate(LocalDate.of(1999, 9, 9)).drunkenBeers(List.of(perla_bezalkoholowa, perla_miodowa)).build();

        studentService.create(janek);
        studentService.create(kuba);
        studentService.create(asia);
        studentService.create(ania);
    }

}