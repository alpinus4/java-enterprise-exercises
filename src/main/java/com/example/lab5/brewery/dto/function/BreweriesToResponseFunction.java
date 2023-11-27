package com.example.lab5.brewery.dto.function;

import com.example.lab5.brewery.Brewery;
import com.example.lab5.brewery.dto.GetBreweriesResponse;

import java.util.List;
import java.util.function.Function;

public class BreweriesToResponseFunction implements Function<List<Brewery>, GetBreweriesResponse> {
    @Override
    public GetBreweriesResponse apply(List<Brewery> breweries) {
        return GetBreweriesResponse.builder()
                .breweries(breweries.stream()
                        .map(brewery ->  GetBreweriesResponse.Brewery.builder()
                                .id(brewery.getId())
                                .name(brewery.getName())
                                .build())
                        .toList())
                .build();
    }
}
