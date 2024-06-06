package ru.romanchev.autopark.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CarDto {
    private Long id;
    private Long uniqueNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date buildDate;
    private Long ownerId;
}
