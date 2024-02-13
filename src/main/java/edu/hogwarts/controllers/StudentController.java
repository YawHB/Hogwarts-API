package edu.hogwarts.controllers;

import edu.hogwarts.models.Student;
import edu.hogwarts.repositories.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public Student getStudent(@PathVariable int id) {
        Optional<Student> student =  studentRepository.findById(id); //We use Optional in order to be able to handle if there is no match by id
        return student.orElse(null); // orElse betyder: returnér objektet hvis det findes, ellers returnér det der står i parentesen ("null" i voes tilfælde).

    }




}
