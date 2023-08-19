package com.jiat.crud_application.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.getEmployeeById", query = "SELECT e FROM Employee e WHERE e.id=:id")
})
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "department")
    private String department;

    @Column(name = "hire_date")
    private LocalDate hire_date;

    @Column(name = "salary")
    private Double salary;

    public int getId() {
        return id;
    }


    public Employee() {

    }

    public Employee(int id, String name, String position, String department, LocalDate hire_date, Double salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
        this.hire_date = hire_date;
        this.salary = salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
