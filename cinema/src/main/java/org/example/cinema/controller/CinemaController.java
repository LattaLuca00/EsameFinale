package org.example.cinema.controller;

import org.example.cinema.model.GenericResponse;
import org.example.cinema.service.CinemaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/{id}")
    public GenericResponse<Float> getAllIncasso(@PathVariable int id) {
        return cinemaService.calcolaSommaDiIncassi(id);
    }
}
