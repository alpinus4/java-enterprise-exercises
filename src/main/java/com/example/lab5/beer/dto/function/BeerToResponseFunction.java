package com.example.lab5.beer.dto.function;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.dto.GetBeerResponse;
import com.example.lab5.beer.model.BeerModel;
import com.example.lab5.brewery.Brewery;
import com.example.lab5.brewery.dto.GetBreweryResponse;

import java.util.function.Function;

public class BeerToResponseFunction implements Function<Beer, GetBeerResponse> {
    @Override
    public GetBeerResponse apply(Beer beer) {
        return GetBeerResponse.builder()
                .id(beer.getId())
                .brewingDate(beer.getBrewingDate())
                .name(beer.getName())
                .alcoholContent(beer.getAlcoholContent())
                .type(beer.getType())
                .version(beer.getVersion())
                .build();
    }
}
