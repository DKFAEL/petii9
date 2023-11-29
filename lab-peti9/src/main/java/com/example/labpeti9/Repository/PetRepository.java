package com.example.labpeti9.Repository;

import com.example.labpeti9.Domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findById(Long id);

    List<Pet> findByNameContaining(String name);
}
