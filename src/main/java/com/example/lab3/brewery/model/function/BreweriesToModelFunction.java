package com.example.lab3.brewery.model.function;

import com.example.lab3.brewery.Brewery;
import com.example.lab3.brewery.model.BreweriesModel;

import java.util.List;
import java.util.function.Function;

public class BreweriesToModelFunction implements Function<List<Brewery>, BreweriesModel> {
    @Override
    public BreweriesModel apply(List<Brewery> breweries) {
        return BreweriesModel.builder()
                .breweries(breweries.stream()
                        .map(brewery ->  BreweriesModel.Brewery.builder()
                                .id(brewery.getId())
                                .name(brewery.getName())
                                .build())
                        .toList())
                .build();
    }
}
