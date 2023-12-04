package com.example.lab5.beer.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import com.example.lab5.beer.Beer;
import com.example.lab5.brewery.Brewery;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class BeerFiltersModel {

    private String name;

    private String brewery_name;

    private Beer.Type type;

    public boolean anyUsed() {
        return name != ""
                || brewery_name != ""
                || type != null;
    }
    
}
