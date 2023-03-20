package org.example.cinema.service.impl;

import org.example.cinema.model.Cinema;
import org.example.cinema.model.GenericResponse;
import org.example.cinema.repository.CinemaRepository;
import org.example.cinema.service.CinemaService;
import org.example.cinema.service.SalaCinematograficaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaServiceImpl implements CinemaService {
    private final SalaCinematograficaService cinematograficaService;
    private final CinemaRepository cinemaRepository;

    public CinemaServiceImpl(SalaCinematograficaService cinematograficaService, CinemaRepository cinemaRepository) {
        this.cinematograficaService = cinematograficaService;
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public GenericResponse<Float> calcolaSommaDiIncassi(int idCinema) {
        GenericResponse<Float> totale = new GenericResponse<>();
        float tot = 0f;
        Optional<Cinema> cinema = cinemaRepository.findById(idCinema);
        if (cinema.isPresent()) {
            List<GenericResponse<Float>> valori = cinema.get().getSale().stream()
                    .map(salaCinematografica -> cinematograficaService.calcolaIncasso(salaCinematografica.getId()))
                    .toList();

            for (GenericResponse<Float> g : valori) {
                tot = tot + g.getBody();

            }
            totale.setBody(tot);
        } else {
            totale.setErrorMessage("Cinema non presente");
        }

        return totale;
    }
}
