package org.example.cinema.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
public class SalaCinematografica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany
    private List<Spettatore> spettatori;
    @OneToOne
    @JoinColumn(name = "idfilm")
    private Film film;

}
