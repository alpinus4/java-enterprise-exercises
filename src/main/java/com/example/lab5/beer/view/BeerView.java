package com.example.lab5.beer.view;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.BeerService;
import com.example.lab5.beer.model.BeerModel;
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
public class BeerView implements Serializable {
    private final BeerService beerService;

    private final ModelFunctionFactory modelFunctionFactory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private BeerModel beerModel;

    @Inject
    public BeerView(BeerService beerService, ModelFunctionFactory modelFunctionFactory) {
        this.beerService = beerService;
        this.modelFunctionFactory = modelFunctionFactory;
    }

    public void init() throws IOException {
        Optional<Beer> beer = beerService.find(id);
        if (beer.isPresent()) {
            beerModel = modelFunctionFactory.beerToModel().apply(beer.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Beer not found");
        }
    }
}
