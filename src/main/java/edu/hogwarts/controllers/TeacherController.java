package edu.hogwarts.controllers;

import edu.hogwarts.dto.TeacherDTO;
import edu.hogwarts.models.House;
import edu.hogwarts.models.Teacher;
import edu.hogwarts.repositories.TeacherRepository;
import edu.hogwarts.repositories.HouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;

@RestController
public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }



    //h1************************ GET *****************************
    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }


    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable int id) { //ResponseEntity bruges til at pakke svaret (enten teacher objektet eller en fejlmeddelelse) sammen med den tilsvarende HTTP-statuskode.
        Optional<Teacher> teacher =  teacherRepository.findById(id); // Vi bruger Optional for at kunne håndtere hvis Teacher objektet ikke findes
       return ResponseEntity.of(teacher); //Tjekker om person objektet findes. Gør den det, returneres et RespinseEntity med OK 200. Gør den ikke returneres end 404 NOT FOUND
    }



    //h1************************ POST *****************************
   /* @PostMapping("/teachers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        Optional<House> house = teacherRepository.findByName(teacherDTO.getHouseName());
        if(house.isPresent()) {
            House house1 = house.get();
            Teacher newTeacher = new Teacher(
                    teacherDTO.getFirstName(),
                    teacherDTO.getMiddleName(),
                    teacherDTO.getLastName(),
                    teacherDTO.getDateOfBirth(),
                    house1, // Use the retrieved House object
                    teacherDTO.getEmployment(),
                    teacherDTO.getEmploymentStart(),
                    teacherDTO.getEmploymentEnd()
            );

            Teacher savedTeacher = teacherRepository.save(newTeacher);
            return ResponseEntity.ok(savedTeacher);
        } else{
            return ResponseEntity.notFound().build();
        }

    }*/

    //h1************************ POST *****************************
    //Tidligere post request hvor man skal give hele house objeketet med
    @PostMapping("/teachers")
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher createTeacher(@RequestBody Teacher newTeacher) {

        return teacherRepository.save(newTeacher);
    }

    //h1************************ PUT *****************************
    @PutMapping("/teachers/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int id, @RequestBody Teacher newTeacherData) {
         Optional<Teacher> original = teacherRepository.findById(id);
         if(original.isPresent()) {
             Teacher currentTeacher = original.get();
             currentTeacher.setFirstName(newTeacherData.getFirstName());
             currentTeacher.setLastName(newTeacherData.getLastName());
             currentTeacher.setMiddleName(newTeacherData.getMiddleName());
             currentTeacher.setDateOfBirth(newTeacherData.getDateOfBirth());
             currentTeacher.setHouse(newTeacherData.getHouse());
             currentTeacher.setEmploymentStart(newTeacherData.getEmploymentStart());
             currentTeacher.setEmploymentEnd(newTeacherData.getEmploymentEnd());

             Teacher updatedTeacher = teacherRepository.save(currentTeacher);// Gemmer den opdaterede student i databasen
             return  ResponseEntity.ok().body(updatedTeacher);// Returnerer en HTTP request 200 med det opdaterede student objekt

         } else {
             return ResponseEntity.notFound().build();// ResponseEntity.notFound() skaber en HTTP-respons med statuskoden 404 (Not Found).
         }
    }

    //h1************************ DELETE *****************************
    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable int id) {
        Optional<Teacher> teacherToDelete = teacherRepository.findById(id);
        teacherRepository.deleteById(id);
        return ResponseEntity.of(teacherToDelete);

    }


}
