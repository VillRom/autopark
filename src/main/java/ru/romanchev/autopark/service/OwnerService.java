package ru.romanchev.autopark.service;

import ru.romanchev.autopark.model.dto.OwnerDto;

public interface OwnerService {
    void addOwner(OwnerDto ownerDto);
    //TODO Сделать метод для вывода инфо о машинах
}
