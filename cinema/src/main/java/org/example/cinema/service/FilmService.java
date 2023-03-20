package org.example.cinema.service;

import org.example.cinema.model.Film;

import java.util.List;
import java.util.Optional;


public interface FilmService {

    public Optional<Film> getById(int id);

    public List<Film> getAll();

    public Film insert(String titolo, String autore, String produttore, String genere, int etaMinima, int durata);

    public boolean deleteAll();

    public boolean deleteById(int id);
}
