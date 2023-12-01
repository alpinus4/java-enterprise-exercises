package com.example.lab5.beer;

import com.example.lab5.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NoArgsConstructor;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
@NoArgsConstructor(force = true)
public class BeerRepository implements Repository<Beer, UUID> {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    
    public Optional<Beer> find(UUID id) {
        return Optional.ofNullable(em.find(Beer.class, id));
    }

    @Override
    
    public List<Beer> findAll() {
        return em.createQuery("select b from Beer b", Beer.class).getResultList();
    }

    @Override
    
    public void create(Beer entity) {
        em.persist(entity);
    }

    @Override
    
    public void delete(Beer entity) {
        em.remove(em.find(Beer.class, entity.getId()));
    }

    @Override
    
    public void update(Beer entity) {
        em.merge(entity);
    }
}
