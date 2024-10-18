package com.example.employee.service;

import java.util.List;
import java.util.Map;
import com.example.employee.dto.EmployeeResponseDto;
import com.example.employee.dto.FindBetweenRequestDto;


public interface EmployeeService {
    EmployeeResponseDto findById(Integer id);
    Map<String, List<EmployeeResponseDto>> groupByName();
    List<EmployeeResponseDto> findBetween(FindBetweenRequestDto findBetweenRequestDto);
}

