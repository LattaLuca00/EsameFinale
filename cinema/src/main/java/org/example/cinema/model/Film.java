package org.example.cinema.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titolo;
    private String autore;
    private String produttore;
    private String genere;
    @Column(name = "etaminima")
    private int etaMinima;
    private int durata;
}
