package ru.romanchev.autopark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanchev.autopark.model.Dealer;

public interface DealerRepository extends JpaRepository<Dealer, Long> {
}
