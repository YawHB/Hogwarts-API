package edu.hogwarts.repositories;

import edu.hogwarts.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
   // @Query("SELECT c FROM Course c WHERE c.teacher IS NULL AND c.students IS EMPTY") Ideen er god, men virker ikke helt endnu
   // List<Course> findAllWithoutTeacherAndStudents();


}
