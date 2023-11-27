package com.example.lab5.brewery.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetBreweryResponse {

    private UUID id;

    private LocalDate establishmentDate;

    private String name;

    private String location;
}
