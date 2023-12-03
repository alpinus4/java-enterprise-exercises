package com.example.lab5.brewery.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;

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

        private Long version;

        private LocalDateTime createdAtDateTime;

        private LocalDateTime updatedAtDateTime;

        private float alcoholContent;

    }

    private List<Beer> beers;
}
