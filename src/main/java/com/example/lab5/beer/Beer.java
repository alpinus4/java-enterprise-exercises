package com.example.lab5.beer;

import com.example.lab5.brewery.Brewery;
import com.example.lab5.student.Student;
import com.example.lab5.util.VersionAndTimestampsAuditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "beers")
public class Beer extends VersionAndTimestampsAuditable implements Serializable {
    public enum Type {
        LIGHT, DARK;
    }

    @Id
    private UUID id;
    private String name;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "brewery_id")
    private Brewery brewery;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "student_id")
    private Student student;

    private Type type;

    private LocalDate brewingDate;

    private float alcoholContent;
}
