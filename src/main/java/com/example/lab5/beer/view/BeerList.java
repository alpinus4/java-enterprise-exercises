package com.example.lab5.beer.view;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.BeerService;
import com.example.lab5.beer.model.BeerFiltersModel;
import com.example.lab5.beer.model.BeersModel;
import com.example.lab5.component.ModelFunctionFactory;

@ViewScoped
@Named
public class BeerList implements Serializable {

    private final ModelFunctionFactory factory;

    private BeerService beerService;

    @Getter
    @Setter
    private BeerFiltersModel beerFiltersModel;

    private BeersModel beers;

    @Inject
    public BeerList(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    public Beer.Type[] getBeerTypes() {
        return Beer.Type.values();
    }

    @EJB
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    public void init() {
        beerFiltersModel = BeerFiltersModel.builder().build();
    }

    public BeersModel getBeers() {
        if (beers == null) {
            beers = factory.beersToModel().apply(beerService.findAll());
        }
        return beers;
    }

    public void search() {
        if (beerFiltersModel.anyUsed()) {
            beers = factory.beersToModel().apply(beerService.search(beerFiltersModel));
        } else {
            beers = factory.beersToModel().apply(beerService.findAll());
        }
    }

    
}
