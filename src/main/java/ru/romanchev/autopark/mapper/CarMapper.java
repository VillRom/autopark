package ru.romanchev.autopark.mapper;

import lombok.experimental.UtilityClass;
import ru.romanchev.autopark.model.Car;
import ru.romanchev.autopark.model.Owner;
import ru.romanchev.autopark.model.dto.CarDto;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class CarMapper {
    public static CarDto carToDto(Car car) {
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setNumber(car.getNumber());
        dto.setOwnerId(car.getOwner().getId());
        dto.setBuildDate(car.getBuildDate());
        return dto;
    }

    public static Car dtoToCar(CarDto dto, Owner owner) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setNumber(dto.getNumber());
        car.setOwner(owner);
        car.setBuildDate(dto.getBuildDate());
        return car;
    }

    public static Set<CarDto> carsToDtos(Set<Car> cars) {
        return cars.stream().map(CarMapper::carToDto).collect(Collectors.toSet());
    }
}
