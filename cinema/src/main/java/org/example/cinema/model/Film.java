package org.example.cinema.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Film {
    private int id;
    private String titolo;
    private String autore;
    private String produttore;
    private String genere;
    private int etaMinima;
    private int durata;
}
