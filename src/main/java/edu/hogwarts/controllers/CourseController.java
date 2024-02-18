package edu.hogwarts.controllers;

import edu.hogwarts.models.Course;
import edu.hogwarts.models.Student;
import edu.hogwarts.models.Teacher;
import edu.hogwarts.repositories.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {


    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    //************************** GET COURSES *********************************//
    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }


    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        Optional<Course> course = courseRepository.findById(id);
        return ResponseEntity.of(course);

    }

    //************************** GET TEACHER BELONGING TO COURSE *********************************//
    @GetMapping("/courses/{id}/teacher")
    public ResponseEntity<Teacher> getTeacherBelongingToCourse(@PathVariable int id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            Course courseFound = course.get();
            Teacher teacher = courseFound.getTeacher();
            if (teacher != null) {

                return ResponseEntity.ok().body(teacher);
            } else {
                return ResponseEntity.notFound().build();  //teacher not found
            }

        } else {
            return ResponseEntity.notFound().build(); //course not found
        }
    }


    //************************** GET STUDENTS BELONGING TO COURSE *********************************//

    @GetMapping("/courses/{id}/students")
    public List<Student> getAllStudentsFromCourse(@PathVariable int id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()) {
           Course courseFound = course.get();
           List<Student> students = courseFound.getStudents();
           if(students != null ) {
               return students;
           } else {
               return null; //No students in course
           }

        } else {
            return null; //Course not found
        }

    }


    //************************** POST *********************************//


    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public Course CreateCourse(@RequestBody Course newCourse) {
        return courseRepository.save(newCourse);


    }

    //************************ PUT *****************************
    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable int id) {
        Optional<Course> original = courseRepository.findById(id);
        if (original.isPresent()) {
            Course currentCourse = original.get();
            currentCourse.setCurrent(course.isCurrent());
            currentCourse.setSubject(course.getSubject());
            currentCourse.setSchoolYear(course.getSchoolYear());

            Course updatedCourse = courseRepository.save(currentCourse);
            return ResponseEntity.ok().body(updatedCourse);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //************************ DELETE *****************************
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable int id) {
        Optional<Course> courseToDelete = courseRepository.findById(id);
        courseRepository.deleteById(id);
        return ResponseEntity.of(courseToDelete);

    }

}
