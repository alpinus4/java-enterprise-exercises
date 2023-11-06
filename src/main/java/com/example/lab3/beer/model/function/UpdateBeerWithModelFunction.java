package com.example.lab3.beer.model.function;

import com.example.lab3.beer.Beer;
import com.example.lab3.beer.model.BeerEditModel;
import com.example.lab3.brewery.Brewery;
import io.vavr.Function3;

import java.util.List;
import java.util.function.BiFunction;
import java.io.Serializable;

public class UpdateBeerWithModelFunction implements Function3<Beer, BeerEditModel, List<Brewery>, Beer>, Serializable {
    @Override
    public Beer apply(Beer beer, BeerEditModel request, List<Brewery> breweries) {
        return Beer.builder()
                .id(beer.getId())
                .brewingDate(request.getBrewingDate())
                .name(request.getName())
                .alcoholContent(request.getAlcoholContent())
                .type(request.getType())
                .brewery(breweries.stream()
                        .filter(brewery -> brewery.getId().equals(request.getBrewery().getId()))
                        .findFirst().orElse(beer.getBrewery()))
                .build();
    }
}
