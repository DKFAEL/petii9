package com.example.labpeti9.Service;

import com.example.labpeti9.Domain.Pet;
import com.example.labpeti9.Domain.Tutor;
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

    public TutorDTO registerTutor(TutorDTO tutorDTO) {
        Tutor tutor = mapTutorDTOToEntity(tutorDTO);
        Tutor savedTutor = tutorRepository.save(tutor);
        return mapTutorEntityToDTO(savedTutor);
    }

    public List<TutorDTO> getAllTutors() {
        List<Tutor> tutors = tutorRepository.findAll();
        return tutors.stream().map(this::mapTutorEntityToDTO).collect(Collectors.toList());
    }

    public TutorDTO getTutorById(Long tutorId) {
        Optional<Tutor> tutorOptional = tutorRepository.findById(tutorId);
        return tutorOptional.map(this::mapTutorEntityToDTO).orElse(null);
    }

    public List<TutorDTO> getTutorsByName(String name) {
        List<Tutor> tutors = tutorRepository.findByNameContaining(name);
        return tutors.stream().map(this::mapTutorEntityToDTO).collect(Collectors.toList());
    }

    private Tutor mapTutorDTOToEntity(TutorDTO tutorDTO) {
        Tutor tutor = new Tutor();
        BeanUtils.copyProperties(tutorDTO, tutor);
        List<Pet> pets = tutorDTO.getPets().stream().map(this::mapPetDTOToEntity).collect(Collectors.toList());
        pets.forEach(pet -> pet.setTutor(tutor));
        tutor.setPets(pets);
        return tutor;
    }


    private TutorDTO mapTutorEntityToDTO(Tutor tutor) {
        TutorDTO tutorDTO = new TutorDTO();
        BeanUtils.copyProperties(tutor, tutorDTO);


        List<PetDTO> petDTOs = tutor.getPets().stream().map(this::mapPetEntityToDTO).collect(Collectors.toList());

        tutorDTO.setPets(petDTOs);
        return tutorDTO;
    }


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
