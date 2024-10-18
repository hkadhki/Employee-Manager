package com.example.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.employee.dto.EmployeeResponseDto;
import com.example.employee.dto.FindBetweenRequestDto;
import com.example.employee.exceptions.ErrorInputDataException;
import com.example.employee.mapper.EmployeeMapper;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void findByIdTest(){
        //given
        Optional<Employee> employee = Optional.of(new Employee(
            1,"name","surname",Date.valueOf("2002-02-02"),"department", 10000));
        when(employeeRepository.findById(1)).thenReturn(employee);
        EmployeeResponseDto employeeResponseDtoTest = new EmployeeResponseDto(
             "name","surname",Date.valueOf("2002-02-02"),"department", 10000);
        when(employeeMapper.toEmployeeResponseDto(employee.get())).thenReturn(employeeResponseDtoTest);

        //when
        EmployeeResponseDto employeeResponseDto = employeeService.findById(1);

        //then
        assertEquals(employeeResponseDtoTest, employeeResponseDto);
    }

    @Test
    void findByIdExceptionTest() {
        //given
        when(employeeRepository.findById(1)).thenReturn(Optional.empty());

        //when
        Throwable exception = assertThrowsExactly(ErrorInputDataException.class,
                ()->{employeeService.findById(1);} );

        //then
        assertEquals(ErrorInputDataException.class, exception.getClass());
    }


    @Test
    void findBetweenTest(){
        //given
        Optional<Employee> employee = Optional.of(new Employee(
            1,"name","surname",Date.valueOf("2002-02-02"),"department", 10000));
        List<Employee> list = new ArrayList<>();
        list.add(employee.get());
        when(employeeRepository.findBetweenDate(Date.valueOf("2000-02-02"), Date.valueOf("2004-02-02"))).thenReturn(list);
        EmployeeResponseDto employeeResponseDtoTest = new EmployeeResponseDto(
            "name","surname",Date.valueOf("2002-02-02"),"department", 10000);

        List<EmployeeResponseDto> list2 = new ArrayList<>();
        list2.add(employeeResponseDtoTest);
        when(employeeMapper.toListEmployeeResponseDto(list)).thenReturn(list2);
        FindBetweenRequestDto findBetweenRequestDto = new FindBetweenRequestDto(LocalDate.of(2000,02,02), LocalDate.of(2004,02,02));  

        //when
        List<EmployeeResponseDto> listEmployeeResponseDto = employeeService.findBetween(findBetweenRequestDto);

        //then
        assertEquals(list2, listEmployeeResponseDto);
    }


    @Test
    void groupByNameTest(){
        //given
        Optional<Employee> employee = Optional.of(new Employee(
            1,"name","surname",Date.valueOf("2002-02-02"),"department", 10000));
        List<Employee> list = new ArrayList<>();
        list.add(employee.get());
        when(employeeRepository.findAll()).thenReturn(list);
        EmployeeResponseDto employeeResponseDtoTest = new EmployeeResponseDto(
             "name","surname",Date.valueOf("2002-02-02"),"department", 10000);
        when(employeeMapper.toEmployeeResponseDto(employee.get())).thenReturn(employeeResponseDtoTest);
        List<EmployeeResponseDto> list2 = new ArrayList<>();
        list2.add(employeeResponseDtoTest);
        Map<String,List<EmployeeResponseDto>> map1 = new HashMap<>();
        map1.put("name", list2);

        //when
        Map<String, List<EmployeeResponseDto>> map2 = employeeService.groupByName();

        //then
        assertEquals(map2, map1);
    }
}
