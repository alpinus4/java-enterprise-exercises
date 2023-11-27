package com.example.lab5.beer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class BeerService {

    private BeerRepository repository;

    @Inject
    public BeerService(BeerRepository repository) {
        this.repository = repository;
    }

    public Optional<Beer> find(UUID id) {
        return repository.find(id);
    }

    public List<Beer> findAll() {
        return repository.findAll();
    }

    public void create(Beer entity) {
        repository.create(entity);
    }

    public void update(Beer entity) {
        repository.update(entity);
    }

    public void delete(UUID id) {
        var beer = repository.find(id);
        if (beer.isPresent()) {
            repository.delete(beer.get());
        } else {
            throw new NotFoundException();
        }
    }
}
