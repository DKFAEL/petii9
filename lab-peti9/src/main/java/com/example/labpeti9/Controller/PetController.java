package com.example.labpeti9.Controller;
import com.example.labpeti9.Dto.PetDTO;
import com.example.labpeti9.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/{tutorId}")
    public ResponseEntity<PetDTO> registerPet(@PathVariable Long tutorId, @RequestBody PetDTO petDTO) {
        PetDTO savedPet = petService.registerPet(tutorId, petDTO);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable Long petId) {
        PetDTO petDTO = petService.getPetById(petId);
        return petDTO != null ? ResponseEntity.ok(petDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> getPetsByName(@RequestParam String name) {
        List<PetDTO> pets = petService.getPetsByName(name);
        return ResponseEntity.ok(pets);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Void> updatePetName(@PathVariable Long petId, @RequestParam String newName) {
        petService.updatePetName(petId, newName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable Long petId) {
        petService.deletePet(petId);
        return ResponseEntity.ok().build();
    }



    // Outros métodos relacionados a Pet
}
