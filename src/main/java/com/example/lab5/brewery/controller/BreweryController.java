package com.example.lab5.brewery.controller;

import com.example.lab5.brewery.dto.GetBreweriesResponse;
import com.example.lab5.brewery.dto.GetBreweryResponse;
import com.example.lab5.brewery.dto.PatchBreweryRequest;
import com.example.lab5.brewery.dto.PostBreweryRequest;

import java.util.UUID;

public interface BreweryController {

    GetBreweriesResponse getBreweries();

    GetBreweryResponse getBrewery(UUID id);

    void postBrewery(PostBreweryRequest request);

    void deleteBrewery(UUID id);

    void patchBrewery(UUID id, PatchBreweryRequest request);
}
