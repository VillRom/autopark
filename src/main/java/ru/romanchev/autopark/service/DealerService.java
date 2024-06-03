package ru.romanchev.autopark.service;

import ru.romanchev.autopark.model.dto.DealerDto;
import ru.romanchev.autopark.model.dto.DealerOutputDto;

public interface DealerService {
    void addDealer(DealerDto dealerDto);

    void addOwnerToDealer(Long ownerId, Long dealerId);

    void deleteOwnerFromDealer(Long ownerId, Long dealerId);

    DealerOutputDto getDealer(Long dealerId);
}
