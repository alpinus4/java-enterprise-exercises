package com.example.lab5.brewery.dto.function;

import com.example.lab5.brewery.Brewery;
import com.example.lab5.brewery.dto.PostBreweryRequest;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToBreweryFunction implements BiFunction<UUID, PostBreweryRequest, Brewery> {

    @Override
    public Brewery apply(UUID id, PostBreweryRequest postBreweryRequest) {
        return Brewery.builder()
                .id(id)
                .name(postBreweryRequest.getName())
                .establishmentDate(postBreweryRequest.getEstablishmentDate())
                .location(postBreweryRequest.getLocation())
                .build();
    }
}
