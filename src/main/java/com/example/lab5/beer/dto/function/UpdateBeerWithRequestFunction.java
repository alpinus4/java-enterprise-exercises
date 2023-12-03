package com.example.lab5.beer.dto.function;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.dto.PatchBeerRequest;
import com.example.lab5.beer.model.BeerEditModel;
import com.example.lab5.brewery.Brewery;
import io.vavr.Function2;
import io.vavr.Function3;

import java.io.Serializable;
import java.util.List;

public class UpdateBeerWithRequestFunction implements Function2<Beer, PatchBeerRequest, Beer>, Serializable {
    @Override
    public Beer apply(Beer beer, PatchBeerRequest request) {
        return Beer.builder()
                .id(beer.getId())
                .brewingDate(request.getBrewingDate())
                .name(request.getName())
                .alcoholContent(request.getAlcoholContent())
                .type(request.getType())
                .brewery(beer.getBrewery())
                .version(request.getVersion())
                .build();
    }
}
