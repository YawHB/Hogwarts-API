package edu.hogwarts.controllers;

import edu.hogwarts.dto.StudentDTO;
import edu.hogwarts.models.Student;
import edu.hogwarts.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentDTO studentDTO;

    public StudentController(StudentRepository studentRepository, StudentDTO studentDTO) {
        this.studentRepository = studentRepository;
        this.studentDTO =   studentDTO;
    }


    //h1************************ GET *****************************

    @GetMapping()
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
         List<Student> students =  studentRepository.findAll();
         List<StudentDTO> studentDTOList = new ArrayList<>();
         if(!students.isEmpty()) {
             for(Student student: students) {
             StudentDTO studentDTO = new StudentDTO();
             studentDTO.setFirstName(student.getFirstName());
             studentDTO.setLastName(student.getLastName());
             studentDTO.setMiddleName(student.getMiddleName());
             studentDTO.setHouse(student.getHouse().getName());

             studentDTOList.add(studentDTO);

             }
             return ResponseEntity.ok(studentDTOList);
         } else {
             return ResponseEntity.notFound().build();
         }


    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) { //ResponseEntity bruges til at pakke svaret (enten studerendeobjektet eller en fejlmeddelelse) sammen med den tilsvarende HTTP-statuskode.
        Optional<Student> student = studentRepository.findById(id); //We use Optional in order to be able to handle if there is no match by id
        return ResponseEntity.of(student); //Tjekker om person objektet findes. Gør den det, returneres et RespinseEntity med OK 200. Gør den ikke returneres end 404 NOT FOUND


    }

    //h1************************ POST *****************************



    //TODO****************************************************'
    //@PostMapping("/students")
    //@ResponseStatus(HttpStatus.CREATED)
   /* public StudentDTO createStudent(@RequestBody Student student) {
        StudentDTO newStudentDTO = new StudentDTO();

        //return studentRepository.save(student);

    }*/

    //h1************************ PUT *****************************
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student newStudentData) {
        Optional<Student> original = studentRepository.findById(id); //Finder objektet med det tilhørende id
        if (original.isPresent()) { //Checker om objektet findes
            Student currentStudent = original.get(); //finder objektet der matcher id'et og gemmer det i originalStudent variablen

            // Overskriver hver property for currentStudent med de opdaterede værdier fra student
            currentStudent.setDateOfBirth(newStudentData.getDateOfBirth());
            currentStudent.setEnrollmentYear(newStudentData.getEnrollmentYear());
            currentStudent.setFirstName(newStudentData.getFirstName());
            currentStudent.setGraduated(newStudentData.isGraduated());
            currentStudent.setGraduationYear(newStudentData.getGraduationYear());
            currentStudent.setHouse(newStudentData.getHouse());
            currentStudent.setLastName(newStudentData.getLastName());
            currentStudent.setMiddleName(newStudentData.getMiddleName());
            currentStudent.setPrefect(newStudentData.isPrefect());

            Student updatedStudent = studentRepository.save(currentStudent); //Gemmer den opdaterede student i databasen
            return ResponseEntity.ok().body(updatedStudent); // Returnerer en HTTP request 200 med det opdaterede student objekt

        } else {
            return ResponseEntity.notFound().build(); // ResponseEntity.notFound() skaber en HTTP-respons med statuskoden 404 (Not Found).
        }

    }

    //h1************************ DELETE *****************************

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        Optional<Student> studentToDelete = studentRepository.findById(id);
        studentRepository.deleteById(id); // objektet bliver slettet fra databasen
        return ResponseEntity.of(studentToDelete); // Returnerer en statuskode 200 hvis objeket fandtes i DB'en og en 404 hvis objektet ikke fandtes
    }


}
