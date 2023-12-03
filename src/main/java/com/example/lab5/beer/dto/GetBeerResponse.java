package com.example.lab5.beer.dto;

import com.example.lab5.beer.Beer;
import com.example.lab5.brewery.Brewery;
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
public class GetBeerResponse {

    private UUID id;

    private String name;

    private Beer.Type type;

    private LocalDate brewingDate;

    private Long version;

    private float alcoholContent;
}
