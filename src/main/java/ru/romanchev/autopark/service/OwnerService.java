package ru.romanchev.autopark.service;

import ru.romanchev.autopark.model.dto.OwnerDto;

public interface OwnerService {
    OwnerDto addOwner(OwnerDto ownerDto);

    OwnerDto getOwner(Long ownerId);

    OwnerDto addCarToOwner(Long carId, Long ownerId);

    OwnerDto deleteCarFromOwner(Long carId, Long ownerId);
}
