package edu.hogwarts.controllers;

import edu.hogwarts.models.Student;
import edu.hogwarts.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {

         List<Student> students = studentRepository.findAll(); //Get all students returneres fra DB til frontend
         return students;


    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) { //ResponseEntity bruges til at pakke svaret (enten studerendeobjektet eller en fejlmeddelelse) sammen med den tilsvarende HTTP-statuskode.
        Optional<Student> student =  studentRepository.findById(id); //We use Optional in order to be able to handle if there is no match by id
        return ResponseEntity.of(student); //Tjekker om person objektet findes. Gør den det, returneres et RespinseEntity med OK 200. Gør den ikke returneres end 404 NOT FOUND


    }

    //************************ POST *****************************


     @PostMapping("/students")
     @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {

        return studentRepository.save(student);

    }





}
