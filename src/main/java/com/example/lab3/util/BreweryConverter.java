package com.example.lab3.util;

import com.example.lab3.beer.BeerService;
import com.example.lab3.beer.model.BeerEditModel;
import com.example.lab3.brewery.Brewery;
import com.example.lab3.brewery.BreweryService;
import com.example.lab3.brewery.model.BreweriesModel;
import com.example.lab3.brewery.model.BreweryModel;
import com.example.lab3.component.ModelFunctionFactory;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.UUID;


@FacesConverter(forClass = BeerEditModel.Brewery.class, managed = true)
public class BreweryConverter implements Converter<BeerEditModel.Brewery> {

    private final BreweryService breweryService;

    private final BeerService beerService;

    private final ModelFunctionFactory factory;

    @Inject
    public BreweryConverter(BreweryService breweryService, BeerService beerService, ModelFunctionFactory factory) {
        this.breweryService = breweryService;
        this.beerService = beerService;
        this.factory = factory;
    }

    @Override
    public BeerEditModel.Brewery getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        Brewery brewery = breweryService.find(UUID.fromString(value)).orElseThrow();
        return BeerEditModel.Brewery.builder().id(brewery.getId()).name(brewery.getName()).build();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, BeerEditModel.Brewery value) {
        return value == null ? "" : value.getId().toString();
    }
}
