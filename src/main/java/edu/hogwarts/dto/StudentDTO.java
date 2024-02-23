package edu.hogwarts.dto;

import edu.hogwarts.models.House;
import edu.hogwarts.models.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StudentDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String house;
    private boolean prefect;
    private int enrollmentYear;
    private int graduationYear;
    private boolean graduated;

    public StudentDTO() {

    }

    public StudentDTO(String firstName, String middleName, String lastName, String house) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.house = house;
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

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public boolean isPrefect() {
        return prefect;
    }

    public void setPrefect(boolean prefect) {
        this.prefect = prefect;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

    public String getFullName() {
        return firstName + " " + middleName + " " + " " + lastName;
    }


}
