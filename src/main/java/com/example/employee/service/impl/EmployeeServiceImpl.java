package com.example.employee.service.impl;

import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.employee.dto.EmployeeResponseDto;
import com.example.employee.dto.FindBetweenRequestDto;
import com.example.employee.exceptions.ErrorInputDataException;
import com.example.employee.mapper.EmployeeMapper;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;


/**
 * Service implementation for managing {@link Employee} entities. This service provides methods for
 * retrieving employee data based on various criteria
 */
@Slf4j
@Service
public class EmployeeServiceImpl {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    /**
     * Constructs an instance of {@code EmployeeServiceImpl} with the specified {@link EmployeeRepository}
     * and {@link EmployeeMapper}.
     *
     * @param repository the repository used for data access
     * @param mapper the mapper used for converting between entities and DTOs
     */
    public EmployeeServiceImpl(EmployeeRepository repository, EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Finds an {@link Employee} by its ID and converts it to an {@link EmployeeResponseDto}.
     *
     * @param id the ID of the employee to be found
     * @return the {@link EmployeeResponseDto} representing the found employee
     * @throws ErrorInputDataException if the employee with the specified ID is not found
     */
    public EmployeeResponseDto findById(Integer id) {
        log.info("Attempting to find employee with ID {}", id);
        Employee employee = repository.findById(id)
            .orElseThrow(() -> {
                log.error("Employee with ID {} not found", id);
                return new ErrorInputDataException("Employee with ID " + id + " not found");
            });
        log.info("Found employee with ID {}", id);
        return mapper.toEmployeeResponseDto(employee);
    }

    /**
     * Groups employees by their name and returns a map where the key is the employee's name and
     * the value is a list of {@link EmployeeResponseDto} representing employees with that name.
     *
     * @return a {@link Map} where the key is the employee's name and the value is a list of {@link EmployeeResponseDto}
     */
    public Map<String, List<EmployeeResponseDto>> groupByName() {
        log.info("Grouping employees by name");
        List<Employee> allEmployees = repository.findAll();
        Map<String, List<EmployeeResponseDto>> groupedEmployees = allEmployees.stream()
            .collect(Collectors.groupingBy(
                Employee::getName,
                Collectors.mapping(mapper::toEmployeeResponseDto, Collectors.toList())
            ));
        log.info("Grouped {} employees by name", allEmployees.size());
        return groupedEmployees;
    }

    /**
     * Finds all employees whose birth dates fall within the specified date range and converts them to a list of {@link EmployeeResponseDto}.
     *
     * @param findBetweenRequestDto the DTO containing the date range for searching employees
     * @return a {@link List} of {@link EmployeeResponseDto} representing the employees found within the specified date range
     */
    public List<EmployeeResponseDto> findBetween(FindBetweenRequestDto findBetweenRequestDto) {
        log.info("Finding employees born between {} and {}", findBetweenRequestDto.getDateAfter(), findBetweenRequestDto.getDateBefore());
        List<EmployeeResponseDto> employees = mapper.toListEmployeeResponseDto(
            repository.findBetweenDate(
                Date.valueOf(findBetweenRequestDto.getDateAfter()),
                Date.valueOf(findBetweenRequestDto.getDateBefore())
                ));
        log.info("Found {} employees born between the specified dates", employees.size());
        return employees;
    }
}