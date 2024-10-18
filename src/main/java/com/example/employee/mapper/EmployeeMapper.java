package com.example.employee.mapper;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.employee.dto.EmployeeResponseDto;
import com.example.employee.model.Employee;


/**
 * Mapper interface for converting between EmployeeResponseDto and Employee.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    List<EmployeeResponseDto> toListEmployeeResponseDto(List<Employee> employees);

    EmployeeResponseDto toEmployeeResponseDto(Employee employee);
}
