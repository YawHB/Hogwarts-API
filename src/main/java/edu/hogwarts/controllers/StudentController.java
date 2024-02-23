package edu.hogwarts.controllers;

import edu.hogwarts.dto.StudentDTO;
import edu.hogwarts.models.House;
import edu.hogwarts.models.Student;
import edu.hogwarts.repositories.HouseRepository;
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
    private final HouseRepository houseRepository;

    public StudentController(StudentRepository studentRepository, StudentDTO studentDTO, HouseRepository houseRepository) {
        this.studentRepository = studentRepository;
        this.studentDTO =   studentDTO;
        this.houseRepository = houseRepository;
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
             studentDTO.setMiddleName(student.getMiddleName());
             studentDTO.setLastName(student.getLastName());
             studentDTO.setDateOfBirth(student.getDateOfBirth());
             studentDTO.setHouse(student.getHouse().getName());
                 studentDTO.setPrefect(student.isPrefect());
                 studentDTO.setEnrollmentYear(student.getEnrollmentYear());
                 studentDTO.setGraduationYear(student.getGraduationYear());
                 studentDTO.setGraduated(student.isGraduated());

             studentDTOList.add(studentDTO);

             }
             return ResponseEntity.ok(studentDTOList);
         } else {
             return ResponseEntity.notFound().build();
         }


    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable int id) {//ResponseEntity bruges til at pakke svaret (enten studerendeobjektet eller en fejlmeddelelse) sammen med den tilsvarende HTTP-statuskode.
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            Student originalStudent = student.get();
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName(originalStudent.getFirstName());
            studentDTO.setMiddleName(originalStudent.getMiddleName());
            studentDTO.setLastName(originalStudent.getLastName());
            studentDTO.setDateOfBirth(originalStudent.getDateOfBirth());

            studentDTO.setHouse(originalStudent.getHouse().getName());
            studentDTO.setPrefect(originalStudent.isPrefect());
            studentDTO.setEnrollmentYear(originalStudent.getEnrollmentYear());
            studentDTO.setGraduationYear(originalStudent.getGraduationYear());
            studentDTO.setGraduated(originalStudent.isGraduated());

            return ResponseEntity.ok(studentDTO);

        }

        //Tjekker om person objektet findes. Gør den det, returneres et RespinseEntity med OK 200. Gør den ikke returneres end 404 NOT FOUND
        return ResponseEntity.notFound().build();

    }

    //h1************************ POST *****************************


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody StudentDTO studentDTO) {
       Optional<House> house = houseRepository.findByName(studentDTO.getHouse());




        System.out.println("house " + house);
       Student newStudent = new Student();
       newStudent.setFirstName(studentDTO.getFirstName());
       newStudent.setMiddleName(studentDTO.getMiddleName());
       newStudent.setLastName(studentDTO.getLastName());
       newStudent.setDateOfBirth(studentDTO.getDateOfBirth());
       house.ifPresent(newStudent::setHouse); // hvis house objekt findes (ikke null), kald setHouse metoden på newStudent instansen. double colon (::) kaldes en metodehenvisning
       newStudent.setPrefect(studentDTO.isPrefect());
       newStudent.setEnrollmentYear(studentDTO.getEnrollmentYear());
       newStudent.setGraduationYear(studentDTO.getGraduationYear());
       newStudent.setGraduated(studentDTO.isGraduated());

        return studentRepository.save(newStudent);
    }

    //h1************************ PUT *****************************
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody StudentDTO newStudentData) {
        Optional<Student> original = studentRepository.findById(id); //Finder objektet med det tilhørende id
        Optional<House> house = houseRepository.findByName(studentDTO.getHouse());

        if (original.isPresent()) { //Checker om objektet findes
            Student currentStudent = original.get(); //finder objektet der matcher id'et og gemmer det i originalStudent variablen

            // Overskriver hver property for currentStudent med de opdaterede værdier fra student
            currentStudent.setDateOfBirth(newStudentData.getDateOfBirth());
            currentStudent.setEnrollmentYear(newStudentData.getEnrollmentYear());
            currentStudent.setFirstName(newStudentData.getFirstName());
            currentStudent.setGraduated(newStudentData.isGraduated());
            currentStudent.setGraduationYear(newStudentData.getGraduationYear());
            house.ifPresent(currentStudent::setHouse);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        Optional<Student> studentToDelete = studentRepository.findById(id);
        studentRepository.deleteById(id); // objektet bliver slettet fra databasen
        return ResponseEntity.of(studentToDelete); // Returnerer en statuskode 200 hvis objeket fandtes i DB'en og en 404 hvis objektet ikke fandtes
    }


}
