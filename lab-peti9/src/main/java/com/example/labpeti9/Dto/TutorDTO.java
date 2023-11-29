package com.example.labpeti9.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TutorDTO {
    private String name;
    private String nickname;
    private LocalDate dateOfBirth;
    private List<PetDTO> pets;
}
