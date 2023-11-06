package com.example.lab3.brewery;

import com.example.lab3.controller.exception.NotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class BreweryService {

    private BreweryRepository repository;

    @Inject
    public BreweryService(BreweryRepository repository) {
        this.repository = repository;
    }

    public Optional<Brewery> find(UUID id) {
        return repository.find(id);
    }

    public List<Brewery> findAll() {
        return repository.findAll();
    }

    public void create(Brewery entity) {
        repository.create(entity);
    }

    public void delete(UUID id) {
        var brewery = repository.find(id);
        if (brewery.isPresent()) {
            repository.delete(brewery.get());
        } else {
            throw new NotFoundException();
        }
    }
}
