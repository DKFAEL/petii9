package com.example.labpeti9.Domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Tutor")
@Table(name = "Tutor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)

    private String name;

    private String nickname;
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private List<Pet> pets;

}

