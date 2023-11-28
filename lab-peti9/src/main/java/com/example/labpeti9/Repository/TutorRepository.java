package com.example.labpeti9.Repository;

import com.example.labpeti9.Domain.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Optional<Tutor> findByName(String name);
}
