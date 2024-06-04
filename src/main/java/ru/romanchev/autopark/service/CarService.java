package ru.romanchev.autopark.service;

import ru.romanchev.autopark.model.dto.CarDto;

public interface CarService {
    void addCar(CarDto car);

    CarDto addCarToOwner(Long carId, Long ownerId);

    void deleteCarFromOwner(Long carId, Long ownerId);

    CarDto getCar(Long carId);
}
