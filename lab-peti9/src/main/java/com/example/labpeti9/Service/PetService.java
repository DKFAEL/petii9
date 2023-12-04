package com.example.labpeti9.Service;

import com.example.labpeti9.Domain.Pet;
import com.example.labpeti9.Domain.Tutor;
import com.example.labpeti9.Dto.PetDTO;
import com.example.labpeti9.Repository.PetRepository;
import com.example.labpeti9.Repository.TutorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public PetDTO registerPet(Long tutorId, PetDTO petDTO) {
        Optional<Tutor> tutorOptional = tutorRepository.findById(tutorId);

        if (tutorOptional.isPresent()) {
            Tutor tutor = tutorOptional.get();

            // Check if the tutor already has a pet with the same name
            if (tutor.getPets().stream().anyMatch(p -> p.getName().equalsIgnoreCase(petDTO.getName()))) {
                throw new IllegalArgumentException("Tutor already has a pet with the same name");
            }
            Pet pet = mapPetDTOToEntity(petDTO);
            pet.setTutor(tutor);

            Pet savedPet = petRepository.save(pet);
            return mapPetEntityToDTO(savedPet);
        }

        return null;
    }

    public PetDTO getPetById(Long petId) {
        Optional<Pet> petOptional = petRepository.findById(petId);
        return petOptional.map(this::mapPetEntityToDTO).orElse(null);
    }

    public List<PetDTO> getPetsByName(String name) {
        List<Pet> pets = petRepository.findByNameContaining(name);
        return pets.stream().map(this::mapPetEntityToDTO).collect(Collectors.toList());
    }

    public void updatePetName(Long petId, String newName) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));
        pet.setName(newName);
        petRepository.save(pet);
    }

    public void deletePet(Long petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            Tutor tutor = pet.getTutor();

            if (tutor != null && tutor.getPets() != null) {
                tutor.getPets().remove(pet);
                tutorRepository.save(tutor);
            }

            petRepository.deleteById(petId);
        }
    }

    // Outros métodos relacionados a Pet

    private Pet mapPetDTOToEntity(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    private PetDTO mapPetEntityToDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        return petDTO;
    }
}