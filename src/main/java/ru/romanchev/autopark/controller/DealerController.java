package ru.romanchev.autopark.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.romanchev.autopark.model.dto.DealerDto;
import ru.romanchev.autopark.service.DealerService;

@RestController
@RequestMapping("/dealer")
@Slf4j
public class DealerController {

    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping
    public DealerDto addDealer(@RequestBody DealerDto dealerDto) {
        log.info("Add dealer");
        return dealerService.addDealer(dealerDto);
    }

    @PatchMapping("/{dealerId}")
    public DealerDto addOwnerToDealer(@RequestParam Long ownerId,
                                      @PathVariable Long dealerId) {
        log.info("Add owner with id = {} to dealer with id = {}", ownerId, dealerId);
        return dealerService.addOwnerToDealer(ownerId, dealerId);
    }

    @DeleteMapping("/{dealerId}")
    public DealerDto deleteOwnerFromDealer(@RequestParam Long ownerId,
                                           @PathVariable Long dealerId) {
        log.info("Delete owner with id = {} from dealer with id = {}", ownerId, dealerId);
        return dealerService.deleteOwnerFromDealer(ownerId, dealerId);
    }

    @GetMapping("/{dealerId}")
    public DealerDto getDealerWithId(@PathVariable Long dealerId) {
        log.info("Get dealer with id = {}", dealerId);
        return dealerService.getDealer(dealerId);
    }
}
