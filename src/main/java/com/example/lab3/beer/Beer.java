package com.example.lab3.beer;

import com.example.lab3.brewery.Brewery;
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
public class Beer implements Serializable {
    public enum Type {
        LIGHT, DARK;
    }

    private UUID id;
    private String name;

    private Brewery brewery;

    private Type type;

    private LocalDate brewingDate;

    private float alcoholContent;
}
