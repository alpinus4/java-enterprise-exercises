package com.example.lab3.beer.model.function;

import com.example.lab3.beer.Beer;
import com.example.lab3.beer.model.BeerEditModel;
import com.example.lab3.beer.model.BeerModel;

import java.util.function.Function;

public class BeerToEditModelFunction implements Function<Beer, BeerEditModel> {
    @Override
    public BeerEditModel apply(Beer beer) {
        return BeerEditModel.builder()
                .brewingDate(beer.getBrewingDate())
                .name(beer.getName())
                .alcoholContent(beer.getAlcoholContent())
                .type(beer.getType())
                .brewery(BeerEditModel.Brewery.builder().id(beer.getBrewery().getId()).name(beer.getBrewery().getName()).build())
                .build();
    }
}
