package com.example.lab5.beer.dto.function;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.dto.GetBeersResponse;
import com.example.lab5.brewery.Brewery;
import com.example.lab5.brewery.dto.GetBreweriesResponse;

import java.util.List;
import java.util.function.Function;

public class BeersToResponseFunction implements Function<List<Beer>, GetBeersResponse> {
    @Override
    public GetBeersResponse apply(List<Beer> beers) {
        return GetBeersResponse.builder()
                .beers(beers.stream()
                        .map(beer -> GetBeersResponse.Beer.builder()
                                .id(beer.getId())
                                .name(beer.getName())
                                .build())
                        .toList())
                .build();
    }
}
