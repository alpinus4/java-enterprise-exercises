package com.example.lab5.brewery;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
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

    public void update(Brewery entity) {
        repository.update(entity);
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
