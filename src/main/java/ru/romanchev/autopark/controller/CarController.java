package ru.romanchev.autopark.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.romanchev.autopark.model.dto.CarDto;
import ru.romanchev.autopark.model.dto.CarFullInfoDto;
import ru.romanchev.autopark.service.CarService;

@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{carId}")
    public CarFullInfoDto getInfoAboutCar(@PathVariable Long carId) {
        log.info("Get car with id = {}", carId);
        return carService.getCar(carId);
    }

    @PostMapping()
    public CarDto addCar(@RequestBody CarDto dto) {
        log.info("Add car");
        return carService.addCar(dto);
    }
}
