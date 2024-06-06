package ru.romanchev.autopark.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.romanchev.autopark.exception.RequestException;
import ru.romanchev.autopark.mapper.OwnerMapper;
import ru.romanchev.autopark.model.Car;
import ru.romanchev.autopark.model.Owner;
import ru.romanchev.autopark.model.dto.OwnerDto;
import ru.romanchev.autopark.repository.CarRepository;
import ru.romanchev.autopark.repository.OwnerRepository;
import ru.romanchev.autopark.service.OwnerService;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    private final CarRepository carRepository;

    @Override
    public OwnerDto addOwner(OwnerDto ownerDto) {
        return OwnerMapper.ownerToDto(ownerRepository.save(OwnerMapper.dtoToOwner(ownerDto)));
    }

    @Override
    public OwnerDto getOwner(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(()
                -> new EntityNotFoundException("Owner with id = " + ownerId + " not found"));
        return OwnerMapper.ownerToDto(owner);
    }

    @Override
    public OwnerDto addCarToOwner(Long carId, Long ownerId) {
        Car car = carRepository.findById(carId).orElseThrow(()
                -> new EntityNotFoundException("Car with id = " + carId + " not found"));
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(()
                -> new EntityNotFoundException("Owner with id = " + carId + " not found"));
        if (car.getOwner() != null) throw new RuntimeException("У машины уже есть владелец");
        Set<Car> cars = new HashSet<>();
        if (owner.getCars() != null) cars = owner.getCars();
        cars.add(car);
        owner.setCars(cars);
        car.setOwner(owner);
        carRepository.save(car);
        return OwnerMapper.ownerToDto(ownerRepository.save(owner));
    }

    @Override
    public OwnerDto deleteCarFromOwner(Long carId, Long ownerId) {
        Car car = carRepository.findById(carId).orElseThrow(()
                -> new EntityNotFoundException("Car with id = " + carId + " not found"));
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(()
                -> new EntityNotFoundException("Owner with id = " + carId + " not found"));
        if (owner.getCars() == null || !owner.getCars().contains(car)) throw new RequestException("Машина не закреплена " +
                "за этим владельцем");
        Set<Car> cars = owner.getCars();
        cars.remove(car);
        owner.setCars(cars);
        car.setOwner(null);
        carRepository.save(car);
        return OwnerMapper.ownerToDto(ownerRepository.save(owner));
    }
}
