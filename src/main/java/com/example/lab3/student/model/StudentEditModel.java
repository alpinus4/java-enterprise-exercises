package com.example.lab3.student.model;

import lombok.*;

import jakarta.servlet.http.Part;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class StudentEditModel {

    private String name;

    private String surname;

    private LocalDate birthDate;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Beer {

        private UUID id;

        private String name;

        private float alcoholContent;

    }

    private List<StudentModel.Beer> drunkenBeers;

    private Part avatar;
}
