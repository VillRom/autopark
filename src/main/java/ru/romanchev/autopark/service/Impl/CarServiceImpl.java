package ru.romanchev.autopark.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.romanchev.autopark.mapper.CarMapper;
import ru.romanchev.autopark.mapper.OwnerMapper;
import ru.romanchev.autopark.model.Car;
import ru.romanchev.autopark.model.dto.CarDto;
import ru.romanchev.autopark.model.dto.CarFullInfoDto;
import ru.romanchev.autopark.repository.CarRepository;
import ru.romanchev.autopark.repository.OwnerRepository;
import ru.romanchev.autopark.service.CarService;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public CarDto addCar(CarDto car) {
        return CarMapper.carToDto(carRepository.save(CarMapper.dtoToCar(car)));
    }

    @Override
    public CarFullInfoDto getCar(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(()
                -> new EntityNotFoundException("Car with id = " + carId + " not found"));
        if (car.getOwner() != null) return CarMapper.carToFullInfoDto(car, OwnerMapper.ownerToDto(car.getOwner()));
        return CarMapper.carToFullInfoDto(car, null);
    }
}
