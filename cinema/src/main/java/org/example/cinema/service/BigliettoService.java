package org.example.cinema.service;

import org.example.cinema.model.Biglietto;

import java.util.List;
import java.util.Optional;


public interface BigliettoService {

    public Optional<Biglietto> getById(int id);

    public List<Biglietto> getAll();

    public Biglietto insert(int postoASedere, float prezzo);

    public boolean deleteAll();

    public boolean deleteById(int id);
}
