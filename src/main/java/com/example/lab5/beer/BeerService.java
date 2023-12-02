package com.example.lab5.beer;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.lab5.brewery.Brewery;
import com.example.lab5.brewery.BreweryRepository;
import com.example.lab5.student.Student;
import com.example.lab5.student.StudentRepository;
import com.example.lab5.student.StudentRoles;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class BeerService {

    private BeerRepository beerRepository;

    private BreweryRepository breweryRepository;

    private StudentRepository studentRepository;

    private final SecurityContext securityContext;

    @Inject
    public BeerService(BeerRepository beerRepository, BreweryRepository breweryRepository, StudentRepository studentRepository, @SuppressWarnings("CdiInjectionPointsInspection") SecurityContext securityContext) {
        this.beerRepository = beerRepository;
        this.breweryRepository = breweryRepository;
        this.studentRepository = studentRepository;
        this.securityContext = securityContext;
    }

    @RolesAllowed(StudentRoles.ADMIN)
    public Optional<Beer> find(UUID id) {
        return beerRepository.find(id);
    }

    @RolesAllowed(StudentRoles.USER)
    public Optional<Beer> find(Student student, UUID id) {
        return beerRepository.findByIdAndStudent(id, student);
    }

    @RolesAllowed(StudentRoles.ADMIN)
    public List<Beer> findAll() {
        return beerRepository.findAll();
    }

    @RolesAllowed(StudentRoles.USER)
    public List<Beer> findAll(Student student) {
        return beerRepository.findAllByStudent(student);
    }

    @RolesAllowed(StudentRoles.ADMIN)
    public Optional<List<Beer>> findAllByBreweryId(UUID id) {
        return breweryRepository.find(id).map(beerRepository::findAllByBrewery);
    }

    @RolesAllowed(StudentRoles.USER)
    public Optional<Beer> findForCallerPrincipal(UUID id) {
        if (securityContext.isCallerInRole(StudentRoles.ADMIN)) {
            return find(id);
        } else {
            Student student = studentRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                    .orElseThrow(IllegalStateException::new);
            return find(student, id);
        }
    }

    @RolesAllowed(StudentRoles.USER)
    public List<Beer> findAllForCallerPrincipal() {
        if (securityContext.isCallerInRole(StudentRoles.ADMIN)) {
            return findAll();
        } else {
            Student student = studentRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                    .orElseThrow(IllegalStateException::new);
            return findAll(student);
        }
    }

    @RolesAllowed(StudentRoles.USER)
    public List<Beer> findAllByBreweryForCallerPrincipal(UUID breweryId) {
        if (securityContext.isCallerInRole(StudentRoles.ADMIN)) {
            return findAllByBreweryId(breweryId).orElseThrow();
        } else {
            Student student = studentRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                    .orElseThrow(IllegalStateException::new);
            return findAllByBreweryIdAndStudent(breweryId, student);
        }
    }

    @RolesAllowed(StudentRoles.USER)
    public List<Beer> findAllByBreweryIdAndStudent(UUID breweryId, Student student) {
        Optional<Brewery> brewery = breweryRepository.find(breweryId);
        if (brewery.isPresent()) {
            return beerRepository.findAllByBreweryAndStudent(brewery.get(), student);
        } else {
            return List.of();
        }
    }

    @RolesAllowed(StudentRoles.USER)
    public void createForCallerPrincipal(Beer beer) {
        if (beerRepository.find(beer.getId()).isPresent()) {
            throw new BadRequestException("Beer with id: " + beer.getId() + " already exists!");
        }
        if (breweryRepository.find(beer.getBrewery().getId()).isEmpty()) {
            throw new NotFoundException("Brewery with id: " + beer.getBrewery().getId() + " does not exists!");
        }
        Student student = studentRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);
        beer.setStudent(student);
        beerRepository.create(beer);
    }

    @RolesAllowed(StudentRoles.ADMIN)
    public void create(Beer entity) {
        beerRepository.create(entity);
    }

    @RolesAllowed(StudentRoles.USER)
    public void update(Beer entity) {
        checkAdminRoleOrOwner(beerRepository.find(entity.getId()));
        beerRepository.update(entity);
    }

    @RolesAllowed(StudentRoles.USER)
    public void delete(UUID id) {
        checkAdminRoleOrOwner(beerRepository.find(id));
        var beer = beerRepository.find(id);
        if (beer.isPresent()) {
            beerRepository.delete(beer.get());
        } else {
            throw new NotFoundException();
        }
    }

    private void checkAdminRoleOrOwner(Optional<Beer> beer) throws EJBAccessException {
        if (securityContext.isCallerInRole(StudentRoles.ADMIN)) {
            return;
        } else if (securityContext.isCallerInRole(StudentRoles.USER)
            && beer.isPresent()
            && beer.get().getStudent().getLogin().equals(securityContext.getCallerPrincipal().getName())) {
            return;
        }
        throw new EJBAccessException("Caller not authorized.");
    }
}
