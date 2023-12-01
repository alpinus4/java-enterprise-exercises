package com.example.lab5.student;

import com.example.lab5.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
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
        return em.createQuery("select s from Student s", Student.class).getResultList();
    }

    public Optional<Student> findByLogin(String login) {
        try {
            return Optional.of(em.createQuery("select u from Student u where u.login = :login", Student.class)
                    .setParameter("login", login)
                    .getSingleResult());
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
