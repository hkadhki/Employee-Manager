package com.example.employee.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * DTO for find between date
 */
@Data
@AllArgsConstructor
public class FindBetweenRequestDto {
    @Schema(format = "date", example = "2000-01-08")
    private LocalDate dateAfter;

    @Schema(format = "date", example = "2024-01-08")
    private LocalDate dateBefore;
}
