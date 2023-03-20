package org.example.cinema.controller;

import org.example.cinema.model.Film;
import org.example.cinema.service.FilmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/film")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/{id}")
    public Optional<Film> getByid(@PathVariable int id) {
        return filmService.getById(id);
    }

    @PostMapping()
    public Film insert(@RequestBody Film film) {
        return filmService.insert(film.getTitolo(), film.getAutore(), film.getProduttore(), film.getGenere(),
                film.getEtaMinima(), film.getDurata());
    }

    @GetMapping()
    public List<Film> getAll() {
        return filmService.getAll();
    }


    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable int id) {
        return filmService.deleteById(id);
    }

    @DeleteMapping()
    public boolean deleteAll() {
        return filmService.deleteAll();
    }
}
