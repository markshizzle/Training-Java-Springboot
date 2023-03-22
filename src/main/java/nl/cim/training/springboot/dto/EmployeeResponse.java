package nl.cim.training.springboot.dto;
import nl.cim.training.springboot.utilities.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Double funcGroup;
    private LocalDate dateOfBirth;
    private int age;

    public EmployeeResponse(Long id, String firstName, String lastName, String email, Double funcGroup, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.funcGroup = funcGroup;
        this.dateOfBirth = dateOfBirth;

        Period period = Period.between(dateOfBirth, LocalDate.now());
        this.age = DateTimeUtils.getYearsDifference(dateOfBirth, LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getFuncGroup() {
        return funcGroup;
    }

    public void setFuncGroup(Double funcGroup) {
        this.funcGroup = funcGroup;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", funcGroup=" + funcGroup +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                '}';
    }
}
