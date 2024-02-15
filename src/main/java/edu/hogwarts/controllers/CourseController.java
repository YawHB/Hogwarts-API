package edu.hogwarts.controllers;

import edu.hogwarts.models.Course;
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


    //************************** Get *********************************//
    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }


    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        Optional<Course> course = courseRepository.findById(id);
        return ResponseEntity.of(course);

    }


    //************************** POST *********************************//


    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public Course CreateCourse(@RequestBody Course newCourse) {
        return courseRepository.save(newCourse);


    }


}
