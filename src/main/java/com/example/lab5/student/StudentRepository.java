package com.example.lab5.student;

import com.example.lab5.beer.Beer_;
import com.example.lab5.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
@NoArgsConstructor(force = true)
public class StudentRepository implements Repository<Student, UUID> {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    
    public Optional<Student> find(UUID id) {
        return Optional.ofNullable(em.find(Student.class, id));
    }

    @Override
    
    public List<Student> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        query.select(root);
        return em.createQuery(query).getResultList();
    }

    public Optional<Student> findByLogin(String login) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Student> query = cb.createQuery(Student.class);
            Root<Student> root = query.from(Student.class);
            query.select(root).where(cb.equal(root.get(Student_.login), login));
            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    
    public void create(Student entity) {
        em.persist(entity);
    }

    @Override
    
    public void delete(Student entity) {
        em.remove(em.find(Student.class, entity.getId()));
    }

    @Override
    
    public void update(Student entity) {
        em.merge(entity);
    }
}
