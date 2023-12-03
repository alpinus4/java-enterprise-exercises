package com.example.lab5.beer.view;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.BeerService;
import com.example.lab5.beer.model.BeerModel;
import com.example.lab5.brewery.BreweryService;
import com.example.lab5.component.ModelFunctionFactory;

import jakarta.ejb.EJB;
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
public class BeerView implements Serializable {
    private BeerService beerService;

    private final ModelFunctionFactory modelFunctionFactory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private BeerModel beerModel;

    @EJB
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @Inject
    public BeerView(ModelFunctionFactory modelFunctionFactory) {
        this.modelFunctionFactory = modelFunctionFactory;
    }

    public void init() throws IOException {
        Optional<Beer> beer = beerService.findForCallerPrincipal(id);
        if (beer.isPresent()) {
            beerModel = modelFunctionFactory.beerToModel().apply(beer.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Beer not found");
        }
    }
}
