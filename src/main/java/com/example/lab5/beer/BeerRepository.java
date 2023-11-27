package com.example.lab5.beer;

import com.example.lab5.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NoArgsConstructor;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class BeerRepository implements Repository<Beer, UUID> {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public Optional<Beer> find(UUID id) {
        return Optional.ofNullable(em.find(Beer.class, id));
    }

    @Override
    @Transactional
    public List<Beer> findAll() {
        return em.createQuery("select b from Beer b", Beer.class).getResultList();
    }

    @Override
    @Transactional
    public void create(Beer entity) {
        em.persist(entity);
    }

    @Override
    @Transactional
    public void delete(Beer entity) {
        em.remove(em.find(Beer.class, entity.getId()));
    }

    @Override
    @Transactional
    public void update(Beer entity) {
        em.merge(entity);
    }
}
