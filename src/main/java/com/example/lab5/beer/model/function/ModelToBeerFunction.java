package com.example.lab5.beer.model.function;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.model.BeerCreateModel;
import com.example.lab5.brewery.Brewery;
import io.vavr.Function2;

import java.util.List;

public class ModelToBeerFunction implements Function2<BeerCreateModel, List<Brewery>, Beer> {
    @Override
    public Beer apply(BeerCreateModel beerCreateModel, List<Brewery> breweries) {
        return Beer.builder()
                .id(beerCreateModel.getId())
                .brewingDate(beerCreateModel.getBrewingDate())
                .name(beerCreateModel.getName())
                .alcoholContent(beerCreateModel.getAlcoholContent())
                .type(beerCreateModel.getType())
                .brewery(breweries.stream()
                        .filter(brewery -> brewery.getId().equals(beerCreateModel.getBrewery().getId()))
                        .findFirst().orElseThrow())
                .build();
    }
}
