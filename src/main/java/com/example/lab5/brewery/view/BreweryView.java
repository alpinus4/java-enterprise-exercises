package com.example.lab5.brewery.view;

import com.example.lab5.beer.BeerService;
import com.example.lab5.brewery.Brewery;
import com.example.lab5.brewery.BreweryService;
import com.example.lab5.brewery.model.BreweryModel;
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
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class BreweryView implements Serializable {

    private final BreweryService breweryService;

    private final BeerService beerService;

    private final ModelFunctionFactory modelFunctionFactory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private BreweryModel brewery;

    @Inject
    public BreweryView(BreweryService breweryService, BeerService beerService, ModelFunctionFactory modelFunctionFactory) {
        this.breweryService = breweryService;
        this.beerService = beerService;
        this.modelFunctionFactory = modelFunctionFactory;
    }

    public void init() throws IOException {
        Optional<Brewery> brewery = breweryService.find(id);
        if (brewery.isPresent()) {
            this.brewery = modelFunctionFactory.breweryToModel().apply(brewery.get(), beerService.findAll());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Brewery not found");
        }
    }

    public String deleteBeerAction(BreweryModel.Beer beer) {
        beerService.delete(beer.getId());
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }
}
