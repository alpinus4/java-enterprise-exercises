package com.example.lab5.beer.controller;

import com.example.lab5.beer.BeerService;
import com.example.lab5.beer.dto.GetBeerResponse;
import com.example.lab5.beer.dto.GetBeersResponse;
import com.example.lab5.beer.dto.PatchBeerRequest;
import com.example.lab5.beer.dto.PostBeerRequest;
import com.example.lab5.brewery.BreweryService;
import com.example.lab5.component.DtoFunctionFactory;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.UUID;

@Singleton
@Path("/breweries/{breweryid}/beers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BeerRestController implements BeerController{
    private final BeerService beerService;

    private final BreweryService breweryService;

    private final DtoFunctionFactory dtoFunctionFactory;

    private final UriInfo uriInfo;

    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Inject
    public BeerRestController(BeerService beerService, BreweryService breweryService, DtoFunctionFactory dtoFunctionFactory, UriInfo uriInfo) {
        this.beerService = beerService;
        this.breweryService = breweryService;
        this.dtoFunctionFactory = dtoFunctionFactory;
        this.uriInfo = uriInfo;
    }


    @GET
    @Override
    public GetBeersResponse getBeers(@PathParam("breweryid") UUID breweryid) {
        breweryService.find(breweryid).orElseThrow(NotFoundException::new);
        return dtoFunctionFactory.beersToResponse().apply(beerService.findAll().stream().filter(beer -> beer.getBrewery().getId().equals(breweryid)).toList());
    }

    @GET
    @Path("/{id}")
    @Override
    public GetBeerResponse getBeer(@PathParam("breweryid") UUID breweryid, @PathParam("id") UUID id) {
        breweryService.find(breweryid).orElseThrow(NotFoundException::new);
        return dtoFunctionFactory.beerToResponse().apply(beerService.find(id).orElseThrow(NotFoundException::new));
    }

    @POST
    @Override
    public void postBeer(@PathParam("breweryid") UUID breweryid, PostBeerRequest request) {
        try {
            var id = UUID.randomUUID();
            var brewery = breweryService.find(breweryid).orElseThrow(NotFoundException::new);
            beerService.create(dtoFunctionFactory.requestToBeer().apply(id, request, brewery));
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @DELETE
    @Path("/{id}")
    @Override
    public void deleteBeer(@PathParam("breweryid") UUID breweryid, @PathParam("id") UUID id) {
        breweryService.find(breweryid).orElseThrow(NotFoundException::new);
        beerService.find(id).ifPresentOrElse(
                beer -> beerService.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @PATCH
    @Path("/{id}")
    @Override
    public void patchBeer(@PathParam("breweryid") UUID breweryid, @PathParam("id") UUID id, PatchBeerRequest request) {
        breweryService.find(breweryid).orElseThrow(NotFoundException::new);
        beerService.find(id).ifPresentOrElse(
                entity -> beerService.update(dtoFunctionFactory.updateBeer().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
