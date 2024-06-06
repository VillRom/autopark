package ru.romanchev.autopark.mapper;

import lombok.experimental.UtilityClass;
import ru.romanchev.autopark.model.Car;
import ru.romanchev.autopark.model.Owner;
import ru.romanchev.autopark.model.dto.OwnerDto;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class OwnerMapper {
    public static OwnerDto ownerToDto(Owner owner) {
        OwnerDto dto =new OwnerDto();
        dto.setId(owner.getId());
        if (owner.getCars() != null) dto.setCarsId(owner.getCars()
                .stream()
                .map(Car::getId)
                .collect(Collectors.toSet()));
        dto.setEmail(owner.getEmail());
        dto.setPhone(owner.getPhone());
        dto.setFirstName(owner.getFirstName());
        dto.setLastName(owner.getLastName());
        dto.setMiddleName(owner.getMiddleName());
        return dto;
    }

    public static Owner dtoToOwner(OwnerDto dto) {
        Owner owner = new Owner();
        owner.setId(dto.getId());
        owner.setEmail(dto.getEmail());
        owner.setPhone(dto.getPhone());
        owner.setFirstName(dto.getFirstName());
        owner.setLastName(dto.getLastName());
        owner.setMiddleName(dto.getMiddleName());
        return owner;
    }

    public static Set<OwnerDto> ownersToDtos(Set<Owner> owners) {
        return owners.stream().map(OwnerMapper::ownerToDto).collect(Collectors.toSet());
    }
}
