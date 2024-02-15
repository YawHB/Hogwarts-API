package edu.hogwarts.models;

import edu.hogwarts.Enums.EmpType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;

    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;


    @ManyToOne(fetch = FetchType.LAZY)
    private House house;
    private EmpType employment;
    private LocalDate employmentStart;
    private LocalDate employmentEnd;

   // @OneToMany
   //private List<Course> course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public LocalDate getEmploymentStart() {
        return employmentStart;
    }

    public EmpType getEmployment() {
        return employment;
    }

    public void setEmployment(EmpType employment) {
        this.employment = employment;
    }

    public void setEmploymentStart(LocalDate employmentStart) {
        this.employmentStart = employmentStart;
    }

    public LocalDate getEmploymentEnd() {
        return employmentEnd;
    }

    public void setEmploymentEnd(LocalDate employmentEnd) {
        this.employmentEnd = employmentEnd;
    }
}

