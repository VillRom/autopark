package ru.romanchev.autopark.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.romanchev.autopark.mapper.DealerMapper;
import ru.romanchev.autopark.model.dto.DealerDto;
import ru.romanchev.autopark.repository.DealerRepository;
import ru.romanchev.autopark.repository.OwnerRepository;
import ru.romanchev.autopark.service.DealerService;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealerServiceImpl implements DealerService {

    private final DealerRepository dealerRepository;

    private final OwnerRepository ownerRepository;

    @Override
    public void addDealer(DealerDto dealerDto) {
        if (dealerRepository.existsById(dealerDto.getId())) {
            log.info("Дилер {} уже существует", dealerDto);
            return;
        }
        dealerRepository.save(DealerMapper.dtoToDealer(dealerDto,
                ownerRepository.getOwnersByIdIsIn(dealerDto.getOwnersId())));
    }
}
