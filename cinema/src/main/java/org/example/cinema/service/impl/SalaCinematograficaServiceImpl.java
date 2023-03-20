package org.example.cinema.service.impl;

import org.example.cinema.exceptions.FilmVietatoAiMinoriException;
import org.example.cinema.exceptions.SalaAlCompletoException;
import org.example.cinema.model.SalaCinematografica;
import org.example.cinema.model.Spettatore;
import org.example.cinema.service.SalaCinematograficaService;
import org.example.cinema.service.SpettatoreService;

import java.util.List;

public class SalaCinematograficaServiceImpl implements SalaCinematograficaService {

    private final SpettatoreService spettatoreService;
    private static final int MAX_CAPACITY = 2;

    public SalaCinematograficaServiceImpl(SpettatoreService spettatoreService) {
        this.spettatoreService = spettatoreService;
    }

    @Override
    public void svuotaSala(SalaCinematografica salaCinematografica) {
        salaCinematografica.getSpettatori().removeAll(salaCinematografica.getSpettatori());
    }

    @Override
    public void inserisciSpettatore(SalaCinematografica salaCinematografica, Spettatore spettatore) throws SalaAlCompletoException, FilmVietatoAiMinoriException {
        List<Spettatore> spettatori = salaCinematografica.getSpettatori();
        spettatori.add(spettatore);
        if (spettatori.size() > MAX_CAPACITY) {
            spettatori.remove(spettatore);
            throw new SalaAlCompletoException("La sala è al completo");
        }
        if (spettatori.get(spettatori.size() - 1).getAge() < salaCinematografica.getFilm().getEtaMinima()) {
            spettatori.remove(spettatore);
            throw new FilmVietatoAiMinoriException("Sei troppo piccolo per questo film");
        }

    }

    @Override
    public float calcolaIncasso(SalaCinematografica salaCinematografica) {
        return salaCinematografica.getSpettatori().stream()
                .map(spettatoreService::getSconto)
                .reduce(0f, Float::sum);
    }
}
