package ru.romanchev.autopark.service;

import ru.romanchev.autopark.model.dto.CarDto;
import ru.romanchev.autopark.model.dto.CarFullInfoDto;

public interface CarService {
    CarDto addCar(CarDto car);

    CarFullInfoDto getCar(Long carId);
}
