package com.example.lab5.observer;

import com.example.lab5.beer.Beer;
import com.example.lab5.beer.BeerService;
import com.example.lab5.brewery.Brewery;
import com.example.lab5.brewery.BreweryService;
import com.example.lab5.student.Student;
import com.example.lab5.student.StudentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.*;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
@NoArgsConstructor
public class InitializedData {

    private StudentService studentService;

    private BeerService beerService;

    private BreweryService breweryService;

    @EJB
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @EJB
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @EJB
    public void setBreweryService(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @PostConstruct
    @SneakyThrows
    private void init() {

        if (breweryService.findAll().isEmpty()) {

            Student janek = Student.builder()
            .id(UUID.fromString("a4727e51-37fd-48a9-89c4-c71fcad03f66"))
            .name("Jan")
            .surname("Piwny")
            .birthDate(LocalDate.of(1997, 10, 21))
            .build();

            Student kuba = Student.builder()
            .id(UUID.fromString("4cb14aab-1cf6-44c9-8ee8-dfd96576c5e2"))
            .name("Kuba").surname("Mocny")
            .birthDate(LocalDate.of(1998, 11, 11))
            .build();

            Student asia = Student.builder()
            .id(UUID.fromString("2fabeeb8-30d9-40e8-b447-b266438f2329"))
            .name("Joanna").surname("Kowalska")
            .birthDate(LocalDate.of(1999, 9, 9))
            .build();

            Student ania = Student.builder()
            .id(UUID.fromString("26ce434b-1201-4564-a3a1-a4089eebf000"))
            .name("Anna")
            .surname("Kowalska")
            .birthDate(LocalDate.of(1999, 9, 9))
            .build();

            studentService.create(janek);
            studentService.create(kuba);
            studentService.create(asia);
            studentService.create(ania);


            Brewery browary_lubelskie_sa = Brewery.builder()
            .id(UUID.fromString("140c99cd-61fc-49b5-b57a-1bf4b141e9f3"))
            .establishmentDate(LocalDate.of(1846, 1, 1))
            .name("Perla browary lubelskie SA")
            .location("Lublin")
            .build();
            
            breweryService.create(browary_lubelskie_sa);


            Beer perla_miodowa = Beer.builder()
            .id(UUID.fromString("3a2922db-fcf5-4cd3-a243-de5a5af4bdc8"))
            .name("Perla miodowa")
            .brewery(browary_lubelskie_sa)
            .type(Beer.Type.LIGHT)
            .student(janek)
            .brewingDate(LocalDate.of(2023, 9, 9))
            .alcoholContent(6)
            .build();

            Beer perla_chmielowa = Beer.builder()
            .id(UUID.fromString("9bc278e8-34be-4765-8913-595b74acd99c"))
            .name("Perla chmielowa")
            .brewery(browary_lubelskie_sa)
            .type(Beer.Type.LIGHT)
            .student(kuba)
            .brewingDate(LocalDate.of(2023, 9, 10))
            .alcoholContent(6)
            .build();

            Beer perla_export = Beer.builder()
            .id(UUID.fromString("7b2ec6e1-57f1-43d4-8ec4-f6f0d3a90136"))
            .name("Perla Export")
            .brewery(browary_lubelskie_sa)
            .type(Beer.Type.LIGHT)
            .student(ania)
            .brewingDate(LocalDate.of(2023, 9, 10))
            .alcoholContent(5.2F)
            .build();

            Beer perla_mocna = Beer.builder()
            .id(UUID.fromString("95d8b072-4a18-418c-a116-3e8b08f2445e"))
            .name("Perla Mocna")
            .brewery(browary_lubelskie_sa)
            .type(Beer.Type.LIGHT)
            .student(kuba)
            .brewingDate(LocalDate.of(2023, 9, 10))
            .alcoholContent(7.1F)
            .build();

            Beer perla_bezalkoholowa = Beer.builder()
            .id(UUID.fromString("727e6972-9a2f-4820-a559-46af12d018b6"))
            .name("Perla Bezalkoholowa")
            .brewery(browary_lubelskie_sa)
            .type(Beer.Type.LIGHT)
            .student(asia)
            .brewingDate(LocalDate.of(2023, 9, 10))
            .alcoholContent(0F)
            .build();

            beerService.create(perla_miodowa);
            beerService.create(perla_chmielowa);
            beerService.create(perla_export);
            beerService.create(perla_mocna);
            beerService.create(perla_bezalkoholowa);


            Brewery tyskie_browary_ksiazece = Brewery.builder()
            .id(UUID.fromString("15b800d3-ac4b-4546-89f9-4654a50adb42"))
            .establishmentDate(LocalDate.of(1629, 1, 1))
            .name("Tyskie Browary Ksiazece")
            .location("Tychy")
            .build();

            breweryService.create(tyskie_browary_ksiazece);

            Beer tyskie_gronie = Beer.builder()
            .id(UUID.fromString("91d8b072-4b18-418c-a116-3e8b08f2445e"))
            .name("Tyskie Gronie")
            .brewery(tyskie_browary_ksiazece)
            .type(Beer.Type.LIGHT)
            .brewingDate(LocalDate.of(2023, 7, 9))
            .alcoholContent(5.2F)
            .build();

            beerService.create(tyskie_gronie);
        }
    }

    /**
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            if (is != null) {
                return is.readAllBytes();
            } else {
                throw new IllegalStateException("Unable to get resource %s".formatted(name));
            }
        }
    }



}
