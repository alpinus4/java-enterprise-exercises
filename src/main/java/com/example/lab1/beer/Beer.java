package com.example.lab1.beer;

import com.example.lab1.brewery.Brewery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
