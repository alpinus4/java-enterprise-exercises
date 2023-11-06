package com.example.lab3.beer.model.function;

import com.example.lab3.beer.Beer;
import com.example.lab3.beer.model.BeerCreateModel;
import com.example.lab3.brewery.Brewery;
import com.example.lab3.student.Student;
import com.example.lab3.student.model.StudentCreateModel;
import io.vavr.Function2;

import java.util.List;
import java.util.function.Function;

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
