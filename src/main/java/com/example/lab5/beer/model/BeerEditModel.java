package com.example.lab5.beer.model;

import com.example.lab5.beer.Beer;
import com.example.lab5.brewery.model.BreweryEditModel;

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

    private BreweryEditModel brewery;

    private Beer.Type type;

    private LocalDate brewingDate;

    private float alcoholContent;
}
