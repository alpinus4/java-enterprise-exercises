package com.example.lab3.brewery;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Brewery implements Serializable {

    private UUID id;
    private LocalDate establishmentDate;

    private String name;

    private String location;
}
