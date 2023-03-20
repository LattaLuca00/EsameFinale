package org.example.cinema.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Biglietto {

    private int id;
    private int postoASedere;
    private float prezzo;

    public float riduzioneAnziani() {
        return (prezzo * 90) / 100;
    }


    public float riduzioneBambini() {
        return (prezzo * 50) / 100;
    }
}
