package com.example.lab5.beer.model;

import com.example.lab5.beer.Beer;
import com.example.lab5.brewery.Brewery;
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
public class BeerModel {
    public enum Type {
        LIGHT, DARK;
    }

    private UUID id;
    private String name;

    private Brewery brewery;

    private Beer.Type type;

    private LocalDate brewingDate;

    private float alcoholContent;
}
