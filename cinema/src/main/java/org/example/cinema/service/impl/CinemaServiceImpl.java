package org.example.cinema.service.impl;

import org.example.cinema.model.Cinema;
import org.example.cinema.service.CinemaService;
import org.example.cinema.service.SalaCinematograficaService;

public class CinemaServiceImpl implements CinemaService {
    private final SalaCinematograficaService cinematograficaService;

    public CinemaServiceImpl(SalaCinematograficaService cinematograficaService) {
        this.cinematograficaService = cinematograficaService;
    }

    @Override
    public float calcolaSommaDiIncassi(Cinema cinema) {
        return cinema.getSale().stream()
                .map(cinematograficaService::calcolaIncasso)
                .reduce(0f, Float::sum);
    }
}
