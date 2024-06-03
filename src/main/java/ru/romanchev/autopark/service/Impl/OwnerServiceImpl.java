package ru.romanchev.autopark.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.romanchev.autopark.mapper.OwnerMapper;
import ru.romanchev.autopark.model.dto.OwnerDto;
import ru.romanchev.autopark.repository.CarRepository;
import ru.romanchev.autopark.repository.DealerRepository;
import ru.romanchev.autopark.repository.OwnerRepository;
import ru.romanchev.autopark.service.OwnerService;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    private final CarRepository carRepository;

    private final DealerRepository dealerRepository;

    @Override
    public void addOwner(OwnerDto ownerDto) {
        if (ownerRepository.existsById(ownerDto.getId())) {
            log.info("Владелец {} уже существует", ownerDto);
            return;
        }
        ownerRepository.save(OwnerMapper.dtoToOwner(ownerDto, carRepository.getCarsByIdIsIn(ownerDto.getCarsId())));
    }
}
