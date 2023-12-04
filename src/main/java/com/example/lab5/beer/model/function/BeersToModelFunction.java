package com.example.lab5.beer.model.function;

import java.util.List;
import java.util.function.Function;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.model.BeersModel;

public class BeersToModelFunction implements Function<List<Beer>, BeersModel> {

    @Override
    public BeersModel apply(List<Beer> beers) {
        return BeersModel.builder()
                .beers(beers.stream()
                        .map(beer -> new BeerToModelFunction().apply(beer))
                        .toList())
                .build();
    }
}
