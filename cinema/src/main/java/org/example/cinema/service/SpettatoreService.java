package org.example.cinema.service;

import org.example.cinema.model.GenericResponse;
import org.example.cinema.model.Spettatore;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface SpettatoreService {

    public float getSconto(Spettatore spettatore);

    public Optional<Spettatore> getById(int id);

    public List<Spettatore> getAll();

    public GenericResponse<Spettatore> insert(String nome, String cognome, LocalDate dataDiNascita, int idBiglietto);

    public boolean deleteAll();

    public boolean deleteById(int id);
}
