package org.example.cinema.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalaCinematografica {
    private int id;
    private List<Spettatore> spettatori;
    private Film film;

}
