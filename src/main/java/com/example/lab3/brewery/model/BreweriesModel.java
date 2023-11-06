package com.example.lab3.brewery.model;

import com.example.lab3.student.model.StudentsModel;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class BreweriesModel implements Serializable {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Brewery {

        private UUID id;

        private String name;

    }

    @Singular
    private List<Brewery> breweries;
}
