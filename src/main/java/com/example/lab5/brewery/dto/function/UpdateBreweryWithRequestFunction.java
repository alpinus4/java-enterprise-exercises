package com.example.lab5.brewery.dto.function;

import com.example.lab5.brewery.Brewery;
import com.example.lab5.brewery.dto.PatchBreweryRequest;

import java.util.function.BiFunction;

public class UpdateBreweryWithRequestFunction implements BiFunction<Brewery, PatchBreweryRequest, Brewery> {

@Override
public Brewery apply(Brewery brewery, PatchBreweryRequest request) {
        return Brewery.builder()
        .id(brewery.getId())
        .name(request.getName())
        .establishmentDate(request.getEstablishmentDate())
        .location(request.getLocation())
        .build();
        }
}
