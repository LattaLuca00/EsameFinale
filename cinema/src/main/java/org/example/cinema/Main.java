package org.example.cinema;

import org.example.cinema.exceptions.FilmVietatoAiMinoriException;
import org.example.cinema.exceptions.SalaAlCompletoException;
import org.example.cinema.model.*;
import org.example.cinema.service.CinemaService;
import org.example.cinema.service.SalaCinematograficaService;
import org.example.cinema.service.SpettatoreService;
import org.example.cinema.service.impl.CinemaServiceImpl;
import org.example.cinema.service.impl.SalaCinematograficaServiceImpl;
import org.example.cinema.service.impl.SpettatoreServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SpettatoreService spettatoreService = new SpettatoreServiceImpl();
        SalaCinematograficaService cinematograficaService = new SalaCinematograficaServiceImpl(spettatoreService);
        CinemaService cinemaService = new CinemaServiceImpl(cinematograficaService);

        Biglietto biglietto = new Biglietto(1, 1, 10f);
        Spettatore spettatore1 = Spettatore.builder()
                .dataDiNascita(LocalDate.of(2000, 3, 4))
                .biglietto(biglietto)
                .build();
        Spettatore spettatore2 = Spettatore.builder()
                .dataDiNascita(LocalDate.of(2020, 3, 4))
                .biglietto(biglietto)
                .build();
        Spettatore spettatore3 = Spettatore.builder()
                .dataDiNascita(LocalDate.of(1930, 3, 4))
                .biglietto(biglietto)
                .build();
        Film film = Film.builder().etaMinima(1).build();


        List<Spettatore> spettatores = new ArrayList<>();
        SalaCinematografica salaCinematografica = SalaCinematografica.builder().spettatori(spettatores).film(film).build();
        try {
            cinematograficaService.inserisciSpettatore(salaCinematografica, spettatore1);
            cinematograficaService.inserisciSpettatore(salaCinematografica, spettatore2);
            cinematograficaService.inserisciSpettatore(salaCinematografica, spettatore3);

        } catch (SalaAlCompletoException e) {
            throw new RuntimeException(e);
        } catch (FilmVietatoAiMinoriException e) {
            throw new RuntimeException(e);
        }finally {
            List<SalaCinematografica> salaCinematograficaList = new ArrayList<>();
            salaCinematograficaList.add(salaCinematografica);
            Cinema cinema = Cinema.builder().sale(salaCinematograficaList).build();
            System.out.println(cinemaService.calcolaSommaDiIncassi(cinema));
        }

    }
}
