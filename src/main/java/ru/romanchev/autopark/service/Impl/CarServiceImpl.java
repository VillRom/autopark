package ru.romanchev.autopark.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.romanchev.autopark.mapper.CarMapper;
import ru.romanchev.autopark.model.Car;
import ru.romanchev.autopark.model.Owner;
import ru.romanchev.autopark.model.dto.CarDto;
import ru.romanchev.autopark.repository.CarRepository;
import ru.romanchev.autopark.repository.OwnerRepository;
import ru.romanchev.autopark.service.CarService;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final OwnerRepository ownerRepository;

    @Override
    public void addCar(CarDto car, Long ownerId) {
        if (carRepository.existsById(car.getId())) {
            log.info("Автомобиль {} уже существует", car);
            return;
        }
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() ->
        new EntityNotFoundException("Owner with id = " + ownerId + " not found"));
        carRepository.save(CarMapper.dtoToCar(car,owner));
    }

    @Override
    public void addCarToOwner(Long carId, Long ownerId) {
        Car car = carRepository.findById(carId).orElseThrow(()
                -> new EntityNotFoundException("Car with id = " + carId + " not found"));
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(()
                -> new EntityNotFoundException("Owner with id = " + carId + " not found"));
        if (car.getOwner() != null) throw new RuntimeException("У машины уже есть владелец");
        car.setOwner(owner);
        carRepository.save(car);
    }

    @Override
    public void deleteCarFromOwner(Long carId, Long ownerId) {
        Car car = carRepository.findById(carId).orElseThrow(()
                -> new EntityNotFoundException("Car with id = " + carId + " not found"));
        if (!ownerRepository.existsById(ownerId)) throw new EntityNotFoundException("Owner with id = "
                + carId + " not found");
        if (!car.getOwner().getId().equals(ownerId)) throw new RuntimeException("Машина закреплена " +
                "за другим владельцем");
        car.setOwner(null);
        carRepository.save(car);
    }
}
