package org.example.cinema.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cinema {
    private int id;
    private List<SalaCinematografica> sale;
}
