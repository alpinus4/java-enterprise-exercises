package com.example.lab5.brewery;

import com.example.lab5.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
@NoArgsConstructor(force = true)
public class BreweryRepository implements Repository<Brewery, UUID> {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    
    public Optional<Brewery> find(UUID id) {
        return Optional.ofNullable(em.find(Brewery.class, id));
    }

    @Override
    
    public List<Brewery> findAll() {
        return em.createQuery("select b from Brewery b", Brewery.class).getResultList();
    }

    @Override
    
    public void create(Brewery entity) {
        em.persist(entity);
    }

    @Override
    
    public void delete(Brewery entity) {
        em.remove(em.find(Brewery.class, entity.getId()));
    }

    @Override
    
    public void update(Brewery entity) {
        em.merge(entity);
    }
}
