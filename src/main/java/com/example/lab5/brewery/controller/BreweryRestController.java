package com.example.lab5.brewery.controller;

import com.example.lab5.beer.BeerService;
import com.example.lab5.brewery.BreweryService;
import com.example.lab5.brewery.dto.GetBreweriesResponse;
import com.example.lab5.brewery.dto.GetBreweryResponse;
import com.example.lab5.brewery.dto.PatchBreweryRequest;
import com.example.lab5.brewery.dto.PostBreweryRequest;
import com.example.lab5.component.DtoFunctionFactory;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.UUID;
import java.util.logging.Level;

@Log
@Path("/breweries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BreweryRestController implements BreweryController {

    private BreweryService breweryService;

    private final DtoFunctionFactory dtoFunctionFactory;

    private final UriInfo uriInfo;

    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Inject
    public BreweryRestController(DtoFunctionFactory dtoFunctionFactory, UriInfo uriInfo) {
        this.dtoFunctionFactory = dtoFunctionFactory;
        this.uriInfo = uriInfo;
    }

    @EJB
    public void setBreweryService(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @GET
    @Override
    public GetBreweriesResponse getBreweries() {
        return dtoFunctionFactory.breweriesToResponse().apply(breweryService.findAll());
    }


    @GET
    @Path("/{id}")
    @Override
    public GetBreweryResponse getBrewery(@PathParam("id") UUID id) {
        return dtoFunctionFactory.breweryToResponse().apply(breweryService.find(id).orElseThrow(NotFoundException::new));
    }

    @POST
    @Override
    public void postBrewery(PostBreweryRequest request) {
        try {
            var id = UUID.randomUUID();
            breweryService.create(dtoFunctionFactory.requestToBrewery().apply(id, request));
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (EJBException ex) {
            //Any unchecked exception is packed into EJBException. Business exception can be itroduced here.
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @DELETE
    @Path("/{id}")
    @Override
    public void deleteBrewery(@PathParam("id") UUID id) {
        breweryService.find(id).ifPresentOrElse(
                entity -> breweryService.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @PATCH
    @Path("/{id}")
    @Override
    public void patchBrewery(@PathParam("id") UUID id, PatchBreweryRequest request) {
        breweryService.find(id).ifPresentOrElse(
                entity -> breweryService.update(dtoFunctionFactory.updateBrewery().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
