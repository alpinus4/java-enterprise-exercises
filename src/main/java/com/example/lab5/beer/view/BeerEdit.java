package com.example.lab5.beer.view;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.BeerService;
import com.example.lab5.beer.model.BeerEditModel;
import com.example.lab5.brewery.Brewery;
import com.example.lab5.brewery.BreweryService;
import com.example.lab5.brewery.model.BreweryEditModel;
import com.example.lab5.component.ModelFunctionFactory;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

@ViewScoped
@Named
public class BeerEdit implements Serializable {

    private BeerService beerService;

    private BreweryService breweryService;

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

    @EJB
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @EJB
    public void setBreweryService(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @Inject
    public BeerEdit(ModelFunctionFactory modelFunctionFactory) {
        this.modelFunctionFactory = modelFunctionFactory;
    }

    public void init() throws IOException {
        Optional<Beer> beer = beerService.findForCallerPrincipal(id);
        if (beer.isPresent()) {
            this.beer = modelFunctionFactory.beerToEditModel().apply(beer.get());
            this.breweries = breweryService.findAll().stream().map(brewery -> BreweryEditModel.builder().name(brewery.getName()).id(brewery.getId()).build()).toList();
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Beer not found");
        }
    }

    public String saveAction() {
        try {
            beerService.update(modelFunctionFactory.updateBeer().apply(beerService.findForCallerPrincipal(id).orElseThrow(), beer, breweryService.findAll()));
            String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
            return viewId + "?faces-redirect=true&includeViewParams=true";
        } catch (EJBException exception) {
            if (exception.getCause() instanceof OptimisticLockException) {
                Beer current = beerService.findForCallerPrincipal(id).orElseThrow();
                ResourceBundle bundle = ResourceBundle.getBundle("com.example.lab5.view.i18n.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("%s %s".formatted(bundle.getString("errors.version"), current.getVersion())));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("%s %s".formatted(bundle.getString("errors.entity"), current.toString())));
            }
            return null;
        }
    }
}
