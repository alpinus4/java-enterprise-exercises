package com.example.lab5.beer.view;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.BeerService;
import com.example.lab5.beer.model.BeerCreateModel;
import com.example.lab5.beer.model.BeerEditModel;
import com.example.lab5.brewery.BreweryService;
import com.example.lab5.brewery.model.BreweryEditModel;
import com.example.lab5.component.ModelFunctionFactory;

import jakarta.ejb.EJB;
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

    private BeerService beerService;

    private BreweryService breweryService;

    private final ModelFunctionFactory modelFunctionFactory;

    @Getter
    private BeerCreateModel beer;

    @Getter
    private List<BreweryEditModel> breweries;

    public Beer.Type[] getBeerTypes() {
        return Beer.Type.values();
    }

    @EJB
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @EJB
    public void setBreweryService(BreweryService breweryService) {
        this.breweryService = breweryService;
    }


    @Inject
    public BeerCreate(ModelFunctionFactory modelFunctionFactory) {
        this.modelFunctionFactory = modelFunctionFactory;
    }

    public void init() throws IOException {
        var first_brewery = breweryService.findAll().stream().filter(b -> b.getId().equals(breweryid))
                .findFirst().orElseThrow();
        beer = BeerCreateModel.builder()
                    .id(UUID.randomUUID())
                    .brewery(BreweryEditModel.builder().name(first_brewery.getName()).id(first_brewery.getId()).build())
                    .build();
        this.breweries = breweryService.findAll().stream().map(brewery -> BreweryEditModel.builder().name(brewery.getName()).id(brewery.getId()).build()).toList();
    }

    public String saveAction() throws IOException {
        beerService.createForCallerPrincipal(modelFunctionFactory.modelToBeer().apply(beer, breweryService.findAll()));
        return "/brewery/brewery_list.xhtml?faces-redirect=true";
    }

    public Object cancelAction() {
        return "/brewery/brewery_list.xhtml?faces-redirect=true";
    }
}
