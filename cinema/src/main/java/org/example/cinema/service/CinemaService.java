package org.example.cinema.service;

import org.example.cinema.model.Cinema;
import org.example.cinema.model.GenericResponse;

public interface CinemaService {

    public GenericResponse<Float> calcolaSommaDiIncassi(int idCinema);
}
