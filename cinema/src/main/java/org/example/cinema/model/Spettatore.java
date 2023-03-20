package org.example.cinema.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
public class Spettatore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cognome;
    @Column(name = "datadinascita")
    private LocalDate dataDiNascita;

    @Transient
    private int idBiglietto;

    @ManyToOne
    @JoinColumn(name = "idbiglietto")
    private Biglietto biglietto;
    @Transient
    private Boolean maggiorenne;

    public int getAge() {
        return LocalDate.now().getYear() - this.dataDiNascita.getYear();
    }

    public boolean isMaggiorenne() {
        maggiorenne = getAge() >= 18;
        return maggiorenne;
    }
}
