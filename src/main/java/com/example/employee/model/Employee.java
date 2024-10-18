package com.example.employee.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents a employee entity.
 * Entity is mapped to the "employee" table in the database.
 */
@Data
@Table(name = "employee")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    
    /** Unique identifier for the Employee */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Name of the employee */
    @Column(nullable = false)
    private String name;

    /** Surname of the employee */
    @Column(nullable = false)
    private String surname;
    /** Employee's date of birth */
    @Column(nullable = false)
    private Date birthDate;

    /** Department of the employee */
    @Column(nullable = false)
    private String department;

    /** Salary of the employee */
    @Column(nullable = false)
    private Integer salary;
}
