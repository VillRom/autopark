package ru.romanchev.autopark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanchev.autopark.model.Car;

import java.util.Set;

public interface CarRepository extends JpaRepository<Car, Long> {

    Set<Car> getCarsByIdIsIn(Set<Long> ids);
}
