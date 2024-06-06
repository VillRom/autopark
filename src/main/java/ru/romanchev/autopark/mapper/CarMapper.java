package ru.romanchev.autopark.mapper;

import lombok.experimental.UtilityClass;
import ru.romanchev.autopark.model.Car;
import ru.romanchev.autopark.model.dto.CarDto;
import ru.romanchev.autopark.model.dto.CarFullInfoDto;
import ru.romanchev.autopark.model.dto.OwnerDto;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class CarMapper {
    public static CarDto carToDto(Car car) {
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setUniqueNumber(car.getUniqueNumber());
        if (car.getOwner() != null) dto.setOwnerId(car.getOwner().getId());
        dto.setBuildDate(car.getBuildDate());
        return dto;
    }

    public static Car dtoToCar(CarDto dto) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setUniqueNumber(dto.getUniqueNumber());
        car.setBuildDate(dto.getBuildDate());
        return car;
    }

    public static CarFullInfoDto carToFullInfoDto(Car car, OwnerDto owner) {
        CarFullInfoDto carFull = new CarFullInfoDto();
        carFull.setId(car.getId());
        carFull.setUniqueNumber(car.getUniqueNumber());
        carFull.setOwner(owner);
        carFull.setBuildDate(car.getBuildDate());
        return carFull;
    }

    public static Set<CarDto> carsToDtos(Set<Car> cars) {
        return cars.stream().map(CarMapper::carToDto).collect(Collectors.toSet());
    }
}
