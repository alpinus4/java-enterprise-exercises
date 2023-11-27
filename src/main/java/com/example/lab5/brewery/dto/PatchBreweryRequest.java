package com.example.lab5.brewery.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchBreweryRequest {

    private LocalDate establishmentDate;

    private String name;

    private String location;
}
