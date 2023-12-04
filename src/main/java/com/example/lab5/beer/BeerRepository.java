package com.example.lab5.beer;

import com.example.lab5.beer.model.BeerFiltersModel;
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
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;


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
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Beer> query = cb.createQuery(Beer.class);
            Root<Beer> root = query.from(Beer.class);
            query.select(root).where(cb.equal(root.get(Beer_.student), student)).where(cb.equal(root.get(Beer_.id), id));
            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Beer> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Beer> query = cb.createQuery(Beer.class);
        Root<Beer> root = query.from(Beer.class);
        query.select(root);
        return em.createQuery(query).getResultList();
    }

    public List<Beer> findAllByStudent(Student student) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Beer> query = cb.createQuery(Beer.class);
        Root<Beer> root = query.from(Beer.class);
        query.select(root).where(cb.equal(root.get(Beer_.student), student));
        return em.createQuery(query).getResultList();
    }

    public List<Beer> findAllByBrewery(Brewery brewery) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Beer> query = cb.createQuery(Beer.class);
        Root<Beer> root = query.from(Beer.class);
        query.select(root).where(cb.equal(root.get(Beer_.brewery), brewery));
        return em.createQuery(query).getResultList();
    }

    public List<Beer> findAllByBreweryAndStudent(Brewery brewery, Student student) {
                CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Beer> query = cb.createQuery(Beer.class);
        Root<Beer> root = query.from(Beer.class);
        query.select(root).where(cb.equal(root.get(Beer_.student), student)).where(cb.equal(root.get(Beer_.brewery), brewery));
        return em.createQuery(query).getResultList();
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

    public List<Beer> search(BeerFiltersModel beerFiltersModel) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Beer> query = cb.createQuery(Beer.class);
        Root<Beer> root = query.from(Beer.class);

        query.select(root).where(
            cb.and(cb.or(beerFiltersModel.getName() != "" ? cb.like(root.get(Beer_.name), "%"+beerFiltersModel.getName()+"%") : cb.isNotNull(root.get(Beer_.id))),
                   cb.or(beerFiltersModel.getType() != null ? cb.equal(root.get(Beer_.type), beerFiltersModel.getType()) : cb.isNotNull(root.get(Beer_.id))),
                   cb.or(beerFiltersModel.getBrewery_name() != "" ? cb.like(root.get(Beer_.brewery).get("name"), "%"+beerFiltersModel.getBrewery_name()+"%") : cb.isNotNull(root.get(Beer_.id)))
                   )
                   );
        return em.createQuery(query).getResultList();
    }

}
