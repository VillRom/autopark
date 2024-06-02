package ru.romanchev.autopark.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "owners")
@Getter
@Setter
@RequiredArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String phone;

    private String email;

    @Transient
    private Set<Car> cars;
    //TODO добавить в Owner поле Dealer. И переделать таблицу owners(добавить ячейку с dealer_id)
}
