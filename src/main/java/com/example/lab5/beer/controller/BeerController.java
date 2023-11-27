package com.example.lab5.beer.controller;

import com.example.lab5.beer.dto.GetBeerResponse;
import com.example.lab5.beer.dto.GetBeersResponse;
import com.example.lab5.beer.dto.PatchBeerRequest;
import com.example.lab5.beer.dto.PostBeerRequest;
import com.example.lab5.brewery.dto.GetBreweriesResponse;
import com.example.lab5.brewery.dto.GetBreweryResponse;
import com.example.lab5.brewery.dto.PatchBreweryRequest;
import com.example.lab5.brewery.dto.PostBreweryRequest;

import java.util.UUID;

public interface BeerController {

    GetBeersResponse getBeers(UUID breweryid);

    GetBeerResponse getBeer(UUID breweryid, UUID id);

    void postBeer(UUID breweryid, PostBeerRequest request);

    void deleteBeer(UUID breweryid, UUID id);

    void patchBeer(UUID breweryid, UUID id, PatchBeerRequest request);
}
