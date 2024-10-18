package com.example.employee.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * DTO for representing employees
 */
@Data
@AllArgsConstructor
public class EmployeeResponseDto {
    private String name;
    private String surname;
    private Date birthDate;
    private String department;
    private Integer salary;
}
