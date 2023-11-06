package com.example.lab3.brewery;

import com.example.lab3.datastore.DataStore;
import com.example.lab3.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class BreweryRepository implements Repository<Brewery, UUID> {

    private DataStore store;

    @Inject
    public BreweryRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Brewery> find(UUID id) {
        return store.findBrewery(id);
    }

    @Override
    public List<Brewery> findAll() {
        return store.findAllBreweries();
    }

    @Override
    public void create(Brewery entity) {
        store.createBrewery(entity);
    }

    @Override
    public void delete(Brewery entity) {
        store.deleteBrewery(entity);
    }

    @Override
    public void update(Brewery entity) {
        store.updateBrewery(entity);
    }
}
