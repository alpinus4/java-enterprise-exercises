package com.example.lab5.student.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostStudentRequest implements Serializable {


    private String name;

    private String surname;

    private LocalDate birthDate;
    
}
