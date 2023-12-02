package com.example.lab5.brewery;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.lab5.student.StudentRoles;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class BreweryService {

    private BreweryRepository repository;

    @Inject
    public BreweryService(BreweryRepository repository) {
        this.repository = repository;
    }

    @PermitAll
    public Optional<Brewery> find(UUID id) {
        return repository.find(id);
    }

    @PermitAll
    public List<Brewery> findAll() {
        return repository.findAll();
    }

    @RolesAllowed(StudentRoles.ADMIN)
    public void create(Brewery entity) {
        repository.create(entity);
    }

    @RolesAllowed(StudentRoles.ADMIN)
    public void update(Brewery entity) {
        repository.update(entity);
    }

    @RolesAllowed(StudentRoles.ADMIN)
    public void delete(UUID id) {
        var brewery = repository.find(id);
        if (brewery.isPresent()) {
            repository.delete(brewery.get());
        } else {
            throw new NotFoundException();
        }
    }
}
