package com.example.employee.exceptions;

public class ErrorInputDataException extends IllegalArgumentException {
    public ErrorInputDataException(String msg) {
        super(msg);
    }
}
