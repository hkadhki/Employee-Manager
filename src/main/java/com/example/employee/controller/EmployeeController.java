package com.example.employee.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.employee.service.impl.EmployeeServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.employee.dto.EmployeeResponseDto;
import com.example.employee.dto.FindBetweenRequestDto;
import com.example.employee.exceptions.ErrorInputDataException;

import java.util.List;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Controller for managing employee application.
 */
@Tag(name = "Employee api")
@RestController
public class EmployeeController {
    
    private final EmployeeServiceImpl service;

    public EmployeeController(EmployeeServiceImpl service) {
        this.service = service;
    }


    /**
     * Find employee by ID.
     *
     * @param Integer id of the employe
     * @return ResponseEntity with a success message
     * @throws ErrorInputDataException if employe does not exist
     */
    @Operation(
            summary = "Find by ID",
            description = "Find employee by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "This Employe does not exist",
                            responseCode = "400"
                    ),
            }
    )
    @GetMapping("/api/find/{id}")
    public ResponseEntity<EmployeeResponseDto> findById(@PathVariable int id) {
        EmployeeResponseDto employee = service.findById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }



    /**
     * Group employees by name.
     *
     * @return ResponseEntity with a success message
     */
    @Operation(
            summary = "Group by name",
            description = "Group employees by name",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
            }
    )
    @GetMapping("/api/groupByName")
    public ResponseEntity<Map<String, List<EmployeeResponseDto>>> groupByName() {
        Map<String, List<EmployeeResponseDto>> map = service.groupByName();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    /**
     * Updates the title of an existing task.
     *
     * @param FindBetweenRequestDto DateAfter and DateBefore
     * @return a ResponseEntity with a success message
     * @throws ErrorInputDataException if Incorrect input data
     */
    @Operation(
            summary = "Find Between",
            description = "Find employees between date",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Incorrect input data",
                            responseCode = "400"
                    ),
            }
    )
    @PostMapping("/api/findBetween")
    public ResponseEntity<List<EmployeeResponseDto>> findBetween(@RequestBody FindBetweenRequestDto findBetweenRequestDto) {
        List<EmployeeResponseDto> list = service.findBetween(findBetweenRequestDto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
}
