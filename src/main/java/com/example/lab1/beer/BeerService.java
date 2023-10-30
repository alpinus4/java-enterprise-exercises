package com.example.lab1.beer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
}
