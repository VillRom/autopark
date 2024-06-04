package ru.romanchev.autopark.service;

import ru.romanchev.autopark.model.dto.DealerDto;

public interface DealerService {

    DealerDto addDealer(DealerDto dealerDto);

    DealerDto addOwnerToDealer(Long ownerId, Long dealerId);

    void deleteOwnerFromDealer(Long ownerId, Long dealerId);

    DealerDto getDealer(Long dealerId);
}
