package com.example.labpeti9.Service;

import com.example.labpeti9.Domain.Pet;
import com.example.labpeti9.Domain.Tutor;
import com.example.labpeti9.Dto.PetDTO;
import com.example.labpeti9.Dto.TutorDTO;
import com.example.labpeti9.Repository.PetRepository;
import com.example.labpeti9.Repository.TutorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public Pet registerPet(Long tutorId, PetDTO petDTO) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new IllegalArgumentException("Tutor not found"));

        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);

        // Configure the relationship with the tutor
        pet.setTutor(tutor);

        // Business rules
        if (tutor.getPets() != null) {
            // Check if a pet with the same name already exists for the tutor
            if (tutor.getPets().stream().anyMatch(existingPet -> existingPet.getName().equals(pet.getName()))) {
                throw new IllegalArgumentException("A pet with the same name already exists for this tutor.");
            }
        }

        return petRepository.save(pet);
    }

    public Pet getPet(Long petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        return optionalPet.orElse(null);
    }

    public List<Pet> getPetsByName(String name) {
        return petRepository.findByNameContaining(name);
    }

    public void updatePetName(Long petId, String newName) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));
        pet.setName(newName);
        petRepository.save(pet);
    }

    public void deletePet(Long petId) {
        // Before deleting the pet, we should disassociate it from the tutor
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            Tutor tutor = pet.getTutor();
            if (tutor != null) {
                tutor.getPets().remove(pet);
                tutorRepository.save(tutor);
            }
        }
        petRepository.deleteById(petId);
    }
}
