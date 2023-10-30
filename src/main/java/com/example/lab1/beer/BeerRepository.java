package com.example.lab1.beer;

import com.example.lab1.datastore.DataStore;
import com.example.lab1.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class BeerRepository implements Repository<Beer, UUID> {

    private DataStore store;

    @Inject
    public BeerRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Beer> find(UUID id) {
        return store.findBeer(id);
    }

    @Override
    public List<Beer> findAll() {
        return store.findAllBeers();
    }

    @Override
    public void create(Beer entity) {
        store.createBeer(entity);
    }

    @Override
    public void delete(Beer entity) {
        store.deleteBeer(entity);
    }

    @Override
    public void update(Beer entity) {
        store.updateBeer(entity);
    }
}
