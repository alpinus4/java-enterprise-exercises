package com.example.lab5.brewery.dto.function;

import com.example.lab5.brewery.Brewery;
import com.example.lab5.brewery.dto.GetBreweryResponse;

import java.util.function.Function;

public class BreweryToResponseFunction implements Function<Brewery, GetBreweryResponse> {

    @Override
    public GetBreweryResponse apply(Brewery brewery) {
        return GetBreweryResponse.builder()
                .id(brewery.getId())
                .name(brewery.getName())
                .establishmentDate(brewery.getEstablishmentDate())
                .location(brewery.getLocation())
                .build();
    }
}
