package ru.romanchev.autopark.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CarDto {
    private Long id;
    private Long number;
    private LocalDate buildDate;
    private Long ownerId;
}
