package ru.romanchev.autopark.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "cars")
@Getter
@Setter
@RequiredArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uniqueNumber;

    private Date buildDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
