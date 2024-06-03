package ru.romanchev.autopark.model.dto;

import lombok.Data;
import java.util.Set;

@Data
public class OwnerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private String email;
    private Set<Long> carsId;
    private Long dealer_id;
}
