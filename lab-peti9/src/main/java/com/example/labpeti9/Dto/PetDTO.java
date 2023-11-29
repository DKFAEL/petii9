package com.example.labpeti9.Dto;


import com.example.labpeti9.Domain.Tutor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {

    private String name;
    private String breed;
    private LocalDate dateOfBirth;
    private String color;
    private double weight;
    private LocalDate vaccinationDate;
    private String vaccinationType;


}