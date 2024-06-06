package ru.romanchev.autopark.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.romanchev.autopark.exception.RequestException;
import ru.romanchev.autopark.mapper.DealerMapper;
import ru.romanchev.autopark.model.Dealer;
import ru.romanchev.autopark.model.Owner;
import ru.romanchev.autopark.model.dto.DealerDto;
import ru.romanchev.autopark.model.dto.OwnerDto;
import ru.romanchev.autopark.repository.DealerRepository;
import ru.romanchev.autopark.repository.OwnerRepository;
import ru.romanchev.autopark.service.DealerService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealerServiceImpl implements DealerService {

    private final DealerRepository dealerRepository;

    private final OwnerRepository ownerRepository;

    @Override
    public DealerDto addDealer(DealerDto dealerDto) {
        if (dealerDto.getOwners() != null) {
            return DealerMapper.dealerToDto(dealerRepository.save(DealerMapper.dtoToDealer(dealerDto,
                    ownerRepository.getOwnersByIdIsIn(dealerDto.getOwners()
                            .stream()
                            .map(OwnerDto::getId)
                            .collect(Collectors.toSet())))));
        }
        else return DealerMapper.dealerToDto(dealerRepository.save(DealerMapper.dtoToDealer(dealerDto, new HashSet<>())));
    }

    @Override
    public DealerDto addOwnerToDealer(Long ownerId, Long dealerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(()
                -> new EntityNotFoundException("Owner with id = " + ownerId + " not found"));
        Dealer dealer = dealerRepository.findById(dealerId).orElseThrow(()
                -> new EntityNotFoundException("Dealer with id = " + dealerId + " not found"));
        if (dealer.getOwners() != null && dealer.getOwners().contains(owner)) throw new RequestException("The owner is " +
                "already being serviced by the dealer");
        Set<Owner> owners = new HashSet<>();
        if (dealer.getOwners() != null) {
            owners = dealer.getOwners();
        }
        owners.add(owner);
        dealer.setOwners(owners);
        return DealerMapper.dealerToDto(dealerRepository.save(dealer));
    }

    @Override
    public DealerDto deleteOwnerFromDealer(Long ownerId, Long dealerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(()
                -> new EntityNotFoundException("Owner with id = " + ownerId + " not found"));
        Dealer dealer = dealerRepository.findById(dealerId).orElseThrow(()
                -> new EntityNotFoundException("Dealer with id = " + dealerId + " not found"));
        if (!dealer.getOwners().contains(owner)) throw new RequestException("The owner is not " +
                "serviced by the dealer");
        Set<Owner> owners = dealer.getOwners();
        owners.remove(owner);
        dealer.setOwners(owners);
        return DealerMapper.dealerToDto(dealerRepository.save(dealer));
    }

    @Override
    public DealerDto getDealer(Long dealerId) {
        Dealer dealer = dealerRepository.findById(dealerId).orElseThrow(() ->
                new EntityNotFoundException("Dealer with id = " + dealerId + " not found"));
        return DealerMapper.dealerToDto(dealer);
    }
}
