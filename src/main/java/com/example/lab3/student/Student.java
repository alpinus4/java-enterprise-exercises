package com.example.lab3.student;

import com.example.lab3.beer.Beer;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Student implements Serializable {
    private UUID id;

    private String name;

    private String surname;

    private LocalDate birthDate;

    private List<Beer> drunkenBeers;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] avatar;

}
