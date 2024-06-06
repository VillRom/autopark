package ru.romanchev.autopark.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.romanchev.autopark.model.dto.OwnerDto;
import ru.romanchev.autopark.service.OwnerService;

@RestController
@RequestMapping("/owner")
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public OwnerDto addOwner(@RequestBody OwnerDto dto) {
        log.info("Add owner");
        return ownerService.addOwner(dto);
    }

    @GetMapping("/{ownerId}")
    public OwnerDto getOwnerWithId(@PathVariable Long ownerId) {
        log.info("Get owner with id = {}", ownerId);
        return ownerService.getOwner(ownerId);
    }

    @PatchMapping("/{ownerId}")
    public OwnerDto addCarToOwner(@PathVariable Long ownerId,
                                  @RequestParam Long carId) {
        log.info("Add car with id = {} to owner with id = {}", carId, ownerId);
        return ownerService.addCarToOwner(carId, ownerId);
    }

    @DeleteMapping("/{ownerId}")
    public OwnerDto deleteCarFromOwner(@PathVariable Long ownerId,
                                       @RequestParam Long carId) {
        log.info("Delete car with id = {} from owner with id = {}", carId, ownerId);
        return ownerService.deleteCarFromOwner(carId, ownerId);
    }
}
