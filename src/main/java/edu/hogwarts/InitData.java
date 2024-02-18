package edu.hogwarts;

import edu.hogwarts.Enums.EmpType;
import edu.hogwarts.models.Course;
import edu.hogwarts.models.House;
import edu.hogwarts.models.Student;
import edu.hogwarts.models.Teacher;
import edu.hogwarts.repositories.CourseRepository;
import edu.hogwarts.repositories.HouseRepository;
import edu.hogwarts.repositories.StudentRepository;
import edu.hogwarts.repositories.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
public class InitData implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    private final CourseRepository courseRepository;
    private final HouseRepository houseRepository;


    public InitData(StudentRepository studentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository, HouseRepository houseRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.houseRepository = houseRepository;

    }

    House gryffindor;
    House ravenclaw;
    House slytherin;
    House hufflepuff;


    public void run(String... args) {
        System.out.println("InitData is running");


        //************************ HOUSES ******************************
        if (houseRepository.count() == 0) {

            House gryffindor = new House("Gryffindor", "Godric Gryffindor", List.of("Red", "Gold"));
            House slytherin = new House("Slytherin", "Salazar Slytherin", List.of("Green", "Silver"));
            House hufflepuff = new House("Hufflepuff", "Helga Hufflepuff", List.of("Yellow", "Black"));
            House ravenclaw = new House("Ravenclaw", "Rowena Ravenclaw", List.of("Blue", "Bronze"));

            houseRepository.save(gryffindor);
            houseRepository.save(slytherin);
            houseRepository.save(hufflepuff);
            houseRepository.save(ravenclaw);

        }


        //************************ STUDENTS *****************************

        if (studentRepository.count() == 0) {

            ArrayList<Student> students = new ArrayList<>();


            students.add(new Student("Dennis", "Christon", "Helling", LocalDate.of(2016, 12, 12), gryffindor, false, 1880, 1890, false));
            students.add(new Student("Yaw", "", "Jess", LocalDate.of(2024, 2, 13), ravenclaw, true, 2010, 2020, false));
            students.add(new Student("Harry", "James", "Potter", LocalDate.of(1980, 6, 30), gryffindor, true, 1991, 1998, false));
            students.add(new Student("Ron", "Bilius", "Weasley", LocalDate.of(1980, 2, 1), gryffindor, true, 1991, 1998, false));
            students.add(new Student("Draco", "", "Malfoy", LocalDate.of(1980, 5, 5), slytherin, true, 1991, 1998, false));
            students.add(new Student("Luna", "", "Lovegood", LocalDate.of(1981, 1, 13), hufflepuff, true, 1992, 1999, false));
            students.add(new Student("Neville", "Frank", "Longbottom", LocalDate.of(1980, 6, 30), hufflepuff, true, 1991, 1998, false));
            students.add(new Student("Ginevra", "Molly", "Weasley", LocalDate.of(1981, 7, 11), slytherin, true, 1992, 1999, false));
            students.add(new Student("Fred", "", "Weasley", LocalDate.of(1978, 4, 1), hufflepuff, true, 1989, 1996, false));
            students.add(new Student("Xenophilius", "", "Lovegood", LocalDate.of(1950, 12, 29), ravenclaw, true, 1961, 1968, false));
            students.add(new Student("Cedric", "", "Diggory", LocalDate.of(1977, 8, 15), ravenclaw, true, 1989, 1995, true));

            studentRepository.saveAll(students);

        }

        //************************ TEACHERS ******************************

        if (teacherRepository.count() == 0) {

            // List<Course> courses =   courseRepository.findAll();
            ArrayList<Teacher> teachers = new ArrayList<>();


            teachers.add(new Teacher("Severus", "", "Snape", LocalDate.of(1960, 1, 9), null, EmpType.TEMPORARY, LocalDate.of(1981, 9, 1), null));
            teachers.add(new Teacher("Minerva", "", "McGonagall", LocalDate.of(1935, 10, 4), null, EmpType.TENURED, LocalDate.of(1956, 9, 1), null));
            teachers.add(new Teacher("Filius", "", "Flitwick", LocalDate.of(1930, 10, 17), null, EmpType.DECEASED, LocalDate.of(1975, 9, 1), null) );
            teachers.add(new Teacher("Pomona", "", "Sprout", LocalDate.of(1941, 5, 15), null, EmpType.DISCHARGED, LocalDate.of(1974, 9, 1), null));
            teachers.add(new Teacher("Remus", "John", "Lupin", LocalDate.of(1960, 3, 10), null, EmpType.PROBATION, LocalDate.of(1993, 9, 1), LocalDate.of(1994, 6, 18)));
            teachers.add(new Teacher("Sybill", "Patricia", "Trelawney", LocalDate.of(1959, 3, 9), null, EmpType.TENURED, LocalDate.of(1980, 9, 1), null));
            teachers.add(new Teacher("Horace", "", "Slughorn", LocalDate.of(1913, 4, 28), null, EmpType.TEMPORARY, LocalDate.of(1931, 9, 1), null));
            teachers.add(new Teacher("Gilderoy", "", "Lockhart", LocalDate.of(1964, 1, 26), null, EmpType.DECEASED, LocalDate.of(1984, 9, 1), null));
            teachers.add(new Teacher("Dolores", "Jane", "Umbridge", LocalDate.of(1965, 8, 26), null, EmpType.TEMPORARY, LocalDate.of(1990, 9, 1), LocalDate.of(1996, 6, 30)));
            teachers.add(new Teacher("Rubeus", "", "Hagrid", LocalDate.of(1928, 12, 6), null, EmpType.TENURED, LocalDate.of(1963, 9, 1), null));

            teacherRepository.saveAll(teachers);

        }
        //************************ COURSES ******************************

        if (courseRepository.count() == 0) {
            List<Teacher> teachers = teacherRepository.findAll();
            List<Student> students = studentRepository.findAll();

            ArrayList<Course> courses = new ArrayList<>();

           courses.add( new Course("Potions", 1, true, teachers.get(0), students.subList(0, 5)));
            courses.add( new Course("Charms", 5, true, teachers.get(1), students.subList(2, 4)));
            courses.add( new Course("Transfiguration", 3, false, teachers.get(2), students.subList(1, 4)));
            courses.add( new Course("Defense Against the Dark Arts", 2, false, teachers.get(3), students.subList(6, 9)));
            courses.add( new Course("Herbology", 5, true, teachers.get(4), students.subList(0, 6)));

            courseRepository.saveAll(courses);

        }
    }
}
