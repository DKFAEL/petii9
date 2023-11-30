package com.example.labpeti9.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.labpeti9.Dto.TutorDTO;
import com.example.labpeti9.Service.TutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutors")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    public ResponseEntity<TutorDTO> registerTutor(@RequestBody TutorDTO tutorDTO) {
        TutorDTO savedTutor = tutorService.registerTutor(tutorDTO);
        return new ResponseEntity<>(savedTutor, HttpStatus.CREATED);
    }

    @GetMapping("/{tutorId}")
    public ResponseEntity<TutorDTO> getTutorById(@PathVariable Long tutorId) {
        TutorDTO tutorDTO = tutorService.getTutorById(tutorId);
        return tutorDTO != null ? ResponseEntity.ok(tutorDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TutorDTO>> getTutorsByName(@RequestParam String name) {
        List<TutorDTO> tutors = tutorService.getTutorsByName(name);
        return ResponseEntity.ok(tutors);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TutorDTO>> getAllTutors() {
        List<TutorDTO> tutors = tutorService.getAllTutors();
        return ResponseEntity.ok(tutors);
    }

    // Outros métodos relacionados a Tutor
}
