package com.cognizant.wow.employeeregister;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
public class Employee {

    private String lastName;
    private String firstName;
    @Id
    @NotNull
    private Long employeeId;
    private String phoneNumber;


    @JsonCreator
    public Employee(@JsonProperty("lastName") String lastName, @JsonProperty("firstName") String firstName, @JsonProperty("employeeId") Long employeeId, @JsonProperty("phoneNumber") String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.employeeId = employeeId;
        this.phoneNumber = phoneNumber;
    }

    @JsonCreator
    public Employee() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getLastName(), employee.getLastName()) &&
                Objects.equals(getFirstName(), employee.getFirstName()) &&
                getEmployeeId().equals(employee.getEmployeeId()) &&
                Objects.equals(getPhoneNumber(), employee.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastName(), getFirstName(), getEmployeeId(), getPhoneNumber());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", employeeId=" + employeeId +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
