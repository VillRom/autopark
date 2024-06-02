package ru.romanchev.autopark.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.romanchev.autopark.mapper.CarMapper;
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
}
