package org.example.cinema.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Spettatore {
    private int idSpettatore;
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private Biglietto biglietto;
    private Boolean maggiorenne;

    public int getAge() {
        return LocalDate.now().getYear() - this.dataDiNascita.getYear();
    }

    public boolean isMaggiorenne() {
        maggiorenne = getAge() >= 18;
        return maggiorenne;
    }
}
