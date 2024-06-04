package ru.romanchev.autopark.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.romanchev.autopark.exception.RequestException;
import ru.romanchev.autopark.mapper.OwnerMapper;
import ru.romanchev.autopark.model.Owner;
import ru.romanchev.autopark.model.dto.OwnerDto;
import ru.romanchev.autopark.repository.CarRepository;
import ru.romanchev.autopark.repository.OwnerRepository;
import ru.romanchev.autopark.service.OwnerService;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    private final CarRepository carRepository;

    @Override
    public OwnerDto addOwner(OwnerDto ownerDto) {
        if (ownerRepository.existsById(ownerDto.getId())) {
            log.info("Владелец {} уже существует", ownerDto);
            throw new RequestException("The owner with id = " + ownerDto.getId() + " already exists");
        }
        return OwnerMapper.ownerToDto(ownerRepository.save(OwnerMapper.dtoToOwner(ownerDto,
                carRepository.getCarsByIdIsIn(ownerDto.getCarsId()))));
    }

    @Override
    public OwnerDto getOwner(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(()
                -> new EntityNotFoundException("Owner with id = " + ownerId + " not found"));
        return OwnerMapper.ownerToDto(owner);
    }
}
