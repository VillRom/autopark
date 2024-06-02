package ru.romanchev.autopark.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Entity
@Table(name = "dealers")
@Getter
@Setter
@RequiredArgsConstructor
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String email;

    private String firstName;

    private String lastName;

    @Transient
    private Set<Owner> owners;
}
