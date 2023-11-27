package com.example.lab5.brewery.model.function;

import com.example.lab5.beer.Beer;
import com.example.lab5.brewery.Brewery;
import com.example.lab5.brewery.model.BreweryModel;

import java.util.List;
import java.util.function.BiFunction;

public class BreweryToModelFunction implements BiFunction<Brewery, List<Beer>, BreweryModel> {

    @Override
    public BreweryModel apply(Brewery brewery, List<Beer> beerList) {
        var breweryBeers = beerList.stream().filter(beer -> beer.getBrewery().getId().equals(brewery.getId())).toList();
        return BreweryModel.builder()
                .id(brewery.getId())
                .name(brewery.getName())
                .establishmentDate(brewery.getEstablishmentDate())
                .location(brewery.getLocation())
                .beers(breweryBeers.stream()
                        .map(beer ->  BreweryModel.Beer.builder()
                                .id(beer.getId())
                                .name(beer.getName())
                                .alcoholContent(beer.getAlcoholContent())
                                .build())
                        .toList())
                .build();
    }
}
