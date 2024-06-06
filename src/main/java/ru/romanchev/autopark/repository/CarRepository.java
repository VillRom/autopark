package ru.romanchev.autopark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanchev.autopark.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
