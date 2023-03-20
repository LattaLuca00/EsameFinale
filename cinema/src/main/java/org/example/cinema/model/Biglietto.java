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
public class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "postoasedere")
    private int postoASedere;
    private float prezzo;

    public float riduzioneAnziani() {
        return (prezzo * 90) / 100;
    }

    public float riduzioneBambini() {
        return (prezzo * 50) / 100;
    }
}
