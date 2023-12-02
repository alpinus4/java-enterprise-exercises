package com.example.lab5.beer;

import com.example.lab5.brewery.Brewery;
import com.example.lab5.repository.Repository;
import com.example.lab5.student.Student;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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

    public Optional<Beer> findByIdAndStudent(UUID id, Student student) {
        try {
            return Optional.of(em.createQuery("select b from Beer b where b.id = :id and b.student = :student", Beer.class)
                    .setParameter("id", id)
                    .setParameter("student", student)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Beer> findAll() {
        return em.createQuery("select b from Beer b", Beer.class).getResultList();
    }

    public List<Beer> findAllByStudent(Student student) {
        return em.createQuery("select b from Beer b where b.student = :student", Beer.class)
                .setParameter("student", student)
                .getResultList();
    }

    public List<Beer> findAllByBrewery(Brewery brewery) {
        return em.createQuery("select b from Beer b where b.brewery = :brewery", Beer.class)
                .setParameter("brewery", brewery)
                .getResultList();
    }

    public List<Beer> findAllByBreweryAndStudent(Brewery brewery, Student student) {
        return em.createQuery("select b from Beer b where b.brewery = :brewery and b.student = :student", Beer.class)
                .setParameter("student", student)
                .setParameter("brewery", brewery)
                .getResultList();
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
