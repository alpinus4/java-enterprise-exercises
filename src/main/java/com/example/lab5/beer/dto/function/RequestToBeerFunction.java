package com.example.lab5.beer.dto.function;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.dto.PostBeerRequest;
import com.example.lab5.beer.model.BeerCreateModel;
import com.example.lab5.brewery.Brewery;
import io.vavr.Function2;
import io.vavr.Function3;

import java.util.List;
import java.util.UUID;

public class RequestToBeerFunction implements Function3<UUID, PostBeerRequest, Brewery, Beer> {
    @Override
    public Beer apply(UUID id, PostBeerRequest request, Brewery brewery) {
        return Beer.builder()
                .id(id)
                .brewingDate(request.getBrewingDate())
                .name(request.getName())
                .alcoholContent(request.getAlcoholContent())
                .type(request.getType())
                .brewery(brewery)
                .version(request.getVersion())
                .build();
    }
}
