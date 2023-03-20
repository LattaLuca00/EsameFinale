package org.example.cinema.service.impl;

import org.example.cinema.exceptions.FilmVietatoAiMinoriException;
import org.example.cinema.exceptions.SalaAlCompletoException;
import org.example.cinema.model.GenericResponse;
import org.example.cinema.model.SalaCinematografica;
import org.example.cinema.model.Spettatore;
import org.example.cinema.repository.SalaCinematograficaRepository;
import org.example.cinema.service.SalaCinematograficaService;
import org.example.cinema.service.SpettatoreService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalaCinematograficaServiceImpl implements SalaCinematograficaService {

    private final SpettatoreService spettatoreService;
    private final SalaCinematograficaRepository cinematograficaRepository;
    private static final int MAX_CAPACITY = 50;

    public SalaCinematograficaServiceImpl(SpettatoreService spettatoreService, SalaCinematograficaRepository cinematograficaRepository) {
        this.spettatoreService = spettatoreService;
        this.cinematograficaRepository = cinematograficaRepository;
    }

  /*  @Override
    public void svuotaSala(SalaCinematografica salaCinematografica) {
        salaCinematografica.getSpettatori().removeAll(salaCinematografica.getSpettatori());
    }*/

    @Override
    public boolean svuotaSala() {
        Boolean res = Boolean.TRUE;
        try {
            cinematograficaRepository.deleteAll();
        } catch (Exception e) {
            res = Boolean.FALSE;
        }
        return res;
    }

    @Override
    public void inserisciSpettatore(int idSpettatore, int idSala) throws SalaAlCompletoException, FilmVietatoAiMinoriException {
        Optional<Spettatore> spettatore = spettatoreService.getById(idSpettatore);
        Optional<SalaCinematografica> salaCinematografica = cinematograficaRepository.findById(idSala);
        if (spettatore.isPresent() && salaCinematografica.isPresent()) {
            if (salaCinematografica.get().getSpettatori().size() <= MAX_CAPACITY) {
                if (spettatore.get().getAge() > salaCinematografica.get().getFilm().getEtaMinima()) {
                    salaCinematografica.get().getSpettatori().add(spettatore.get());
                } else {
                    throw new FilmVietatoAiMinoriException("Vietato ai minori");
                }
            } else {
                throw new SalaAlCompletoException("Sala al completo");
            }
        }

    }

    @Override
    public GenericResponse<Float> calcolaIncasso(int idSala) {
        GenericResponse<Float> totale=new GenericResponse<>();
        Optional<SalaCinematografica> salaCinematografica = cinematograficaRepository.findById(idSala);
        if (salaCinematografica.isPresent()) {
            totale.setBody(salaCinematografica.get().getSpettatori().stream()
                    .map(spettatoreService::getSconto)
                    .reduce(0f, Float::sum));
        }else{
            totale.setErrorMessage("Sala non trovata");
        }
        return totale;
    }
}
