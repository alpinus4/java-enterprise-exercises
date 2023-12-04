package com.example.lab5.beer.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class BeersModel implements Serializable {

    @Singular
    private List<BeerModel> beers;
}
