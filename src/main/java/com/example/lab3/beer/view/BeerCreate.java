package com.example.lab3.beer.view;

import com.example.lab3.beer.Beer;
import com.example.lab3.beer.BeerService;
import com.example.lab3.beer.model.BeerCreateModel;
import com.example.lab3.beer.model.BeerEditModel;
import com.example.lab3.brewery.BreweryService;
import com.example.lab3.component.ModelFunctionFactory;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ViewScoped
@Named
public class BeerCreate implements Serializable {

    @Setter
    @Getter
    private UUID breweryid;

    private final BeerService beerService;

    private final BreweryService breweryService;

    private final ModelFunctionFactory modelFunctionFactory;

    @Getter
    private BeerCreateModel beer;

    @Getter
    private List<BeerEditModel.Brewery> breweries;

    public Beer.Type[] getBeerTypes() {
        return Beer.Type.values();
    }


    @Inject
    public BeerCreate(BeerService beerService, BreweryService breweryService, ModelFunctionFactory modelFunctionFactory) {
        this.beerService = beerService;
        this.breweryService = breweryService;
        this.modelFunctionFactory = modelFunctionFactory;
    }

    public void init() throws IOException {
        var first_brewery = breweryService.findAll().stream().filter(b -> b.getId().equals(breweryid))
                .findFirst().orElseThrow();
        beer = BeerCreateModel.builder()
                    .id(UUID.randomUUID())
                    .brewery(BeerEditModel.Brewery.builder().name(first_brewery.getName()).id(first_brewery.getId()).build())
                    .build();
        this.breweries = breweryService.findAll().stream().map(brewery -> BeerEditModel.Brewery.builder().name(brewery.getName()).id(brewery.getId()).build()).toList();
    }

    public String saveAction() throws IOException {
        beerService.create(modelFunctionFactory.modelToBeer().apply(beer, breweryService.findAll()));
        return "/brewery/brewery_list.xhtml?faces-redirect=true";
    }

    public Object cancelAction() {
        return "/brewery/brewery_list.xhtml?faces-redirect=true";
    }
}
