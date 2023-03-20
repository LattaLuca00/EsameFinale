package org.example.cinema.service;

import org.example.cinema.exceptions.FilmVietatoAiMinoriException;
import org.example.cinema.exceptions.SalaAlCompletoException;
import org.example.cinema.model.SalaCinematografica;
import org.example.cinema.model.Spettatore;

public interface SalaCinematograficaService {

    public void svuotaSala(SalaCinematografica salaCinematografica);

    public void inserisciSpettatore(SalaCinematografica salaCinematografica, Spettatore spettatore) throws SalaAlCompletoException, FilmVietatoAiMinoriException;

    public float calcolaIncasso(SalaCinematografica salaCinematografica);

}
