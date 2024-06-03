package ru.romanchev.autopark.model.dto;

import lombok.Data;
import ru.romanchev.autopark.model.Car;
import ru.romanchev.autopark.model.Owner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class DealerOutputDto {

    Set<Owner> owners;

    List<Car> cars;

    public void addCarsFromOwners() {
        if (owners.isEmpty()) cars = null;
        Set<Car> carFromOwners = new HashSet<>();
        for (Owner owner : owners) {
            Set<Car> ownerCars = owner.getCars();
            carFromOwners.add((Car) ownerCars);
        }
    }
}
