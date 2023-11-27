package com.example.lab5.beer.model.function;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.model.BeerEditModel;
import com.example.lab5.brewery.Brewery;
import io.vavr.Function3;

import java.io.Serializable;
import java.util.List;

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
