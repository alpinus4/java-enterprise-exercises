package com.example.lab3.brewery.model;

import com.example.lab3.student.model.StudentModel;
import lombok.*;

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
public class BreweryModel {

    private UUID id;

    private LocalDate establishmentDate;

    private String name;

    private String location;

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

    private List<BreweryModel.Beer> beers;
}
