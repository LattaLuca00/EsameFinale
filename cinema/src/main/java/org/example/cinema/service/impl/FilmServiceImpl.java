package org.example.cinema.service.impl;

import org.example.cinema.model.Film;
import org.example.cinema.repository.FilmRepository;
import org.example.cinema.service.FilmService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Optional<Film> getById(int id) {
        return filmRepository.findById(id);
    }

    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film insert(String titolo, String autore, String produttore, String genere, int etaMinima, int durata) {
        return filmRepository.save(Film.builder()
                .titolo(titolo)
                .autore(autore)
                .produttore(produttore)
                .genere(genere)
                .etaMinima(etaMinima)
                .durata(durata)
                .build());
    }


    @Override
    public boolean deleteAll() {
        Boolean res = Boolean.TRUE;
        try {
            filmRepository.deleteAll();
        } catch (Exception e) {
            res = Boolean.FALSE;
        }
        return res;
    }

    @Override
    public boolean deleteById(int id) {
        Boolean res = Boolean.FALSE;
        Optional<Film> film = getById(id);
        if (film.isPresent()) {
            try {
                filmRepository.delete(film.get());
                res = Boolean.TRUE;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                res = Boolean.FALSE;
            }
        }
        return res;
    }
}
