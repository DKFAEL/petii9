package com.example.labpeti9.Service;

import com.example.labpeti9.Domain.Tutor;
import com.example.labpeti9.Domain.Pet;
import com.example.labpeti9.Dto.PetDTO;
import com.example.labpeti9.Dto.TutorDTO;
import com.example.labpeti9.Repository.TutorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public Tutor registerTutor(TutorDTO tutorDTO) {
        Tutor tutor = new Tutor();
        BeanUtils.copyProperties(tutorDTO, tutor);

        // Configure the relationship between pets and the tutor
        List<PetDTO> petDTOs = tutorDTO.getPets();
        if (petDTOs != null) {
            List<Pet> pets = petDTOs.stream()
                    .map(petDTO -> {
                        Pet pet = new Pet();
                        BeanUtils.copyProperties(petDTO, pet);
                        pet.setTutor(tutor);
                        return pet;
                    })
                    .collect(Collectors.toList());
            tutor.setPets(pets);
        }

        // Business rules
        if (tutorRepository.findByName(tutor.getName()).isPresent()) {
            throw new IllegalArgumentException("A tutor with the same name already exists.");
        }

        return tutorRepository.save(tutor);
    }

    public Optional<Tutor> getTutor(Long code) {
        return tutorRepository.findById(code);
    }

    public List<Tutor> getTutorsByName(String name) {
        return tutorRepository.findByNameContaining(name);
    }

    // Other methods as needed
}
