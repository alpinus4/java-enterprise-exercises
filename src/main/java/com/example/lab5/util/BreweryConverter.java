package com.example.lab5.util;

import com.example.lab5.beer.BeerService;
import com.example.lab5.beer.model.BeerEditModel;
import com.example.lab5.brewery.Brewery;
import com.example.lab5.brewery.BreweryService;
import com.example.lab5.brewery.model.BreweryEditModel;
import com.example.lab5.component.ModelFunctionFactory;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.util.UUID;


@FacesConverter(forClass = BreweryEditModel.class, managed = true)
public class BreweryConverter implements Converter<BreweryEditModel> {

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
    public BreweryEditModel getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        Brewery brewery = breweryService.find(UUID.fromString(value)).orElseThrow();
        return BreweryEditModel.builder().id(brewery.getId()).name(brewery.getName()).build();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, BreweryEditModel value) {
        return value == null ? "" : value.getId().toString();
    }
}
