package ru.romanchev.autopark.service;

import ru.romanchev.autopark.model.dto.CarDto;

public interface CarService {
    void addCar(CarDto car, Long ownerId);
}
