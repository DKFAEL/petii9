package com.example.labpeti9.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "Pet")
@Table(name = "Pet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String breed;
    private LocalDate dateOfBirth;
    private String color;
    private double weight;
    private LocalDate vaccinationDate;
    private String vaccinationType;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
}

