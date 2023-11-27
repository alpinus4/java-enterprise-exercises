package com.example.lab5.beer.view;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.BeerService;
import com.example.lab5.beer.model.BeerEditModel;
import com.example.lab5.brewery.Brewery;
import com.example.lab5.brewery.BreweryService;
import com.example.lab5.brewery.model.BreweryEditModel;
import com.example.lab5.component.ModelFunctionFactory;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class BeerEdit implements Serializable {

    private final BeerService beerService;

    private final BreweryService breweryService;

    private final ModelFunctionFactory modelFunctionFactory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private BeerEditModel beer;

    @Getter
    private List<BreweryEditModel> breweries;

    public Beer.Type[] getBeerTypes() {
        return Beer.Type.values();
    }

    @Inject
    public BeerEdit(BeerService beerService, BreweryService breweryService, ModelFunctionFactory modelFunctionFactory) {
        this.beerService = beerService;
        this.breweryService = breweryService;
        this.modelFunctionFactory = modelFunctionFactory;
    }

    public void init() throws IOException {
        Optional<Beer> beer = beerService.find(id);
        if (beer.isPresent()) {
            this.beer = modelFunctionFactory.beerToEditModel().apply(beer.get());
            this.breweries = breweryService.findAll().stream().map(brewery -> BreweryEditModel.builder().name(brewery.getName()).id(brewery.getId()).build()).toList();
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Beer not found");
        }
    }

    public String saveAction() throws IOException {
        beerService.update(modelFunctionFactory.updateBeer().apply(beerService.find(id).orElseThrow(), beer, breweryService.findAll()));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }
}
