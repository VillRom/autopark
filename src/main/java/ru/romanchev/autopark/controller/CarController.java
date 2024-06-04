package ru.romanchev.autopark.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.romanchev.autopark.model.dto.CarDto;
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
    public CarDto getInfoAboutCar(@PathVariable Long carId) {
        log.info("Get car with id = {}", carId);
        return carService.getCar(carId);
    }

    @PostMapping()
    public void addCar(@RequestBody CarDto dto) {
        log.info("Add car");
        carService.addCar(dto);
    }

    @PatchMapping("/{carId}")
    public CarDto addCarToOwner(@PathVariable Long carId,
                                  @RequestParam Long ownerId) {
        log.info("Add car with id = {} to owner with id = {}", carId, ownerId);
        return carService.addCarToOwner(carId, ownerId);
    }

    @DeleteMapping("/{carId}")
    public void deleteCarFromOwner(@PathVariable Long carId,
                                  @RequestParam Long ownerId) {
        log.info("Delete car with id = {} from owner with id = {}", carId, ownerId);
        carService.deleteCarFromOwner(carId, ownerId);
    }
}
