package com.example.lab3.beer.model;

import com.example.lab3.beer.Beer;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class BeerEditModel {

    private String name;

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

    private Brewery brewery;

    private Beer.Type type;

    private LocalDate brewingDate;

    private float alcoholContent;
}
