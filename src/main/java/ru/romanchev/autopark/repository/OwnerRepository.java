package ru.romanchev.autopark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanchev.autopark.model.Owner;

import java.util.Set;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Set<Owner> findAllByIdContaining(Set<Long> ids);
}
