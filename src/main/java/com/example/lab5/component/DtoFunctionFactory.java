package com.example.lab5.component;

import com.example.lab5.beer.dto.function.BeerToResponseFunction;
import com.example.lab5.beer.dto.function.BeersToResponseFunction;
import com.example.lab5.beer.dto.function.RequestToBeerFunction;
import com.example.lab5.beer.dto.function.UpdateBeerWithRequestFunction;
import com.example.lab5.brewery.dto.function.BreweriesToResponseFunction;
import com.example.lab5.brewery.dto.function.BreweryToResponseFunction;
import com.example.lab5.brewery.dto.function.RequestToBreweryFunction;
import com.example.lab5.brewery.dto.function.UpdateBreweryWithRequestFunction;
import com.example.lab5.student.dto.function.RequestToStudentFunction;
import com.example.lab5.student.dto.function.StudentToResponseFunction;
import com.example.lab5.student.dto.function.StudentsToResponseFunction;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DtoFunctionFactory {

    public StudentToResponseFunction studentToResponse(){
        return new StudentToResponseFunction();
    }

    public StudentsToResponseFunction studentsToResponse(){
        return new StudentsToResponseFunction();
    }

    public RequestToStudentFunction requestToStudent(){
        return new RequestToStudentFunction();
    }

    public BreweryToResponseFunction breweryToResponse(){
        return new BreweryToResponseFunction();
    }

    public BreweriesToResponseFunction breweriesToResponse(){
        return new BreweriesToResponseFunction();
    }

    public RequestToBreweryFunction requestToBrewery(){
        return new RequestToBreweryFunction();
    }

    public UpdateBreweryWithRequestFunction updateBrewery(){
        return new UpdateBreweryWithRequestFunction();
    }

    public BeerToResponseFunction beerToResponse(){
        return new BeerToResponseFunction();
    }

    public BeersToResponseFunction beersToResponse(){
        return new BeersToResponseFunction();
    }

    public RequestToBeerFunction requestToBeer(){
        return new RequestToBeerFunction();
    }

    public UpdateBeerWithRequestFunction updateBeer(){
        return new UpdateBeerWithRequestFunction();
    }
}
