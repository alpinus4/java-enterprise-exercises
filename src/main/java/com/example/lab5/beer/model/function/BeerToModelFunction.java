package com.example.lab5.beer.model.function;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.model.BeerModel;

import java.util.function.Function;

public class BeerToModelFunction implements Function<Beer, BeerModel> {
    @Override
    public BeerModel apply(Beer beer) {
        return BeerModel.builder()
                .id(beer.getId())
                .brewingDate(beer.getBrewingDate())
                .name(beer.getName())
                .alcoholContent(beer.getAlcoholContent())
                .type(beer.getType())
                .brewery(beer.getBrewery())
                .version(beer.getVersion())
                .createdAtDateTime(beer.getCreatedAtDateTime())
                .updatedAtDateTime(beer.getUpdatedAtDateTime())
                .build();
    }
}
