package ru.romanchev.autopark.model.dto;

import lombok.Data;
import java.util.Set;

@Data
public class DealerDto {
    private Long id;
    private String title;
    private String email;
    private String firstName;
    private String lastName;
    private Set<Long> ownersId;
}
