package ru.romanchev.autopark.mapper;

import lombok.experimental.UtilityClass;
import ru.romanchev.autopark.model.Dealer;
import ru.romanchev.autopark.model.Owner;
import ru.romanchev.autopark.model.dto.DealerDto;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class DealerMapper {
    public static Dealer dtoToDealer(DealerDto dto, Set<Owner> owners) {
        Dealer dealer = new Dealer();
        dealer.setId(dto.getId());
        dealer.setTitle(dto.getTitle());
        dealer.setFirstName(dto.getFirstName());
        dealer.setLastName(dto.getLastName());
        dealer.setEmail(dto.getEmail());
        dealer.setOwners(owners);
        return dealer;
    }

    public static DealerDto dealerToDto(Dealer dealer) {
        DealerDto dto = new DealerDto();
        dto.setId(dealer.getId());
        dto.setTitle(dealer.getTitle());
        dto.setFirstName(dealer.getFirstName());
        dto.setLastName(dealer.getLastName());
        dto.setEmail(dealer.getEmail());
        dto.setOwnersId(dealer.getOwners().stream().map(Owner::getId).collect(Collectors.toSet()));
        return dto;
    }
}
