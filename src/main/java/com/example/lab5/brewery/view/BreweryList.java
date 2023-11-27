package com.example.lab5.brewery.view;

import com.example.lab5.brewery.BreweryService;
import com.example.lab5.brewery.model.BreweriesModel;
import com.example.lab5.component.ModelFunctionFactory;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class BreweryList {
    private final BreweryService breweryService;

    private BreweriesModel breweries;

    private final ModelFunctionFactory modelFunctionFactory;

    @Inject
    public BreweryList(BreweryService breweryService, ModelFunctionFactory modelFunctionFactory) {
        this.breweryService = breweryService;
        this.modelFunctionFactory = modelFunctionFactory;
    }

    public BreweriesModel getBreweries() {
        if (breweries == null) {
            breweries = modelFunctionFactory.breweriesToModel().apply(breweryService.findAll());
        }

        return breweries;
    }

    public String deleteAction(BreweriesModel.Brewery brewery) {
        breweryService.delete(brewery.getId());
        return "brewery_list?faces-redirect=true";
    }
}
