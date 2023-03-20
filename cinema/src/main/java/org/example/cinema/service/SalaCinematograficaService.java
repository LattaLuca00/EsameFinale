package org.example.cinema.service;

import org.example.cinema.exceptions.FilmVietatoAiMinoriException;
import org.example.cinema.exceptions.SalaAlCompletoException;
import org.example.cinema.model.GenericResponse;

public interface SalaCinematograficaService {

    public boolean svuotaSala();

    public void inserisciSpettatore( int idSpettatore,int idSala) throws SalaAlCompletoException, FilmVietatoAiMinoriException;

    public GenericResponse<Float> calcolaIncasso(int idSala);

}
