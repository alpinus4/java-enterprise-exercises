package com.example.lab5.brewery.view;

import com.example.lab5.beer.BeerService;
import com.example.lab5.brewery.BreweryService;
import com.example.lab5.brewery.model.BreweriesModel;
import com.example.lab5.component.ModelFunctionFactory;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class BreweryList {
    private BreweryService breweryService;

    private BreweriesModel breweries;

    private final ModelFunctionFactory modelFunctionFactory;

    @Inject
    public BreweryList(ModelFunctionFactory modelFunctionFactory) {
        this.modelFunctionFactory = modelFunctionFactory;
    }

    @EJB
    public void setBreweryService(BreweryService breweryService) {
        this.breweryService = breweryService;
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
