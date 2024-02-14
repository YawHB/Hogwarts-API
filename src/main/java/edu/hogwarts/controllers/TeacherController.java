package edu.hogwarts.controllers;

import edu.hogwarts.models.Teacher;
import edu.hogwarts.repositories.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }



    //************************ GET *****************************
    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }


    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable int id) { //ResponseEntity bruges til at pakke svaret (enten teacher objektet eller en fejlmeddelelse) sammen med den tilsvarende HTTP-statuskode.
        Optional<Teacher> teacher =  teacherRepository.findById(id); // Vi bruger Optional for at kunne håndtere hvis Teacher objektet ikke findes
       return ResponseEntity.of(teacher); //Tjekker om person objektet findes. Gør den det, returneres et RespinseEntity med OK 200. Gør den ikke returneres end 404 NOT FOUND
    }



    //************************ POST *****************************
    @PostMapping("/teachers")
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher createTeacher(@RequestBody Teacher newTeacher) {

       return teacherRepository.save(newTeacher);


    }
}
