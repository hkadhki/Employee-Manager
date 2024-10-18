package com.example.employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.example.employee.exceptions.ErrorInputDataException;

@ControllerAdvice
public class CustomExceptionHandler {
    

    @ExceptionHandler(ErrorInputDataException.class)
    public ResponseStatusException errorInputDataException(ErrorInputDataException e) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
