package com.example.lab3.component;

import com.example.lab3.beer.model.function.BeerToEditModelFunction;
import com.example.lab3.beer.model.function.BeerToModelFunction;
import com.example.lab3.beer.model.function.ModelToBeerFunction;
import com.example.lab3.beer.model.function.UpdateBeerWithModelFunction;
import com.example.lab3.brewery.model.function.BreweriesToModelFunction;
import com.example.lab3.brewery.model.function.BreweryToModelFunction;
import com.example.lab3.student.model.function.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Model;

@ApplicationScoped
public class ModelFunctionFactory {

    public StudentToEditModelFunction studentToEditModel() {
        return new StudentToEditModelFunction();
    }

    public StudentsToModelFunction studentsToModel() {
        return new StudentsToModelFunction();
    }

    public StudentToModelFunction studentToModel() {
        return new StudentToModelFunction();
    }

    public UpdateStudentWithModelFunction updateStudent() {
        return new UpdateStudentWithModelFunction();
    }

    public ModelToStudentFunction modelToStudent() {
        return new ModelToStudentFunction();
    }

    public BreweriesToModelFunction breweriesToModel() {
        return new BreweriesToModelFunction();
    }

    public BreweryToModelFunction breweryToModel() {
        return new BreweryToModelFunction();
    }

    public BeerToModelFunction beerToModel() {
        return new BeerToModelFunction();
    }

    public BeerToEditModelFunction beerToEditModel() {
        return new BeerToEditModelFunction();
    }

    public UpdateBeerWithModelFunction updateBeer() {
        return new UpdateBeerWithModelFunction();
    }

    public ModelToBeerFunction modelToBeer() {
        return new ModelToBeerFunction();
    }
}
