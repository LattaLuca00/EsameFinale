package org.example.cinema.service.impl;

import org.example.cinema.model.Biglietto;
import org.example.cinema.repository.BigliettoRepository;
import org.example.cinema.service.BigliettoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BigliettoServiceImpl implements BigliettoService {
    private final BigliettoRepository bigliettoRepository;

    public BigliettoServiceImpl(BigliettoRepository bigliettoRepository) {
        this.bigliettoRepository = bigliettoRepository;
    }

    @Override
    public Optional<Biglietto> getById(int id) {
        return bigliettoRepository.findById(id);
    }

    @Override
    public List<Biglietto> getAll() {
        return bigliettoRepository.findAll();
    }

    @Override
    public Biglietto insert(int postoASedere, float prezzo) {
        return bigliettoRepository.save(Biglietto.builder()
                .postoASedere(postoASedere)
                .prezzo(prezzo)
                .build());
    }


    @Override
    public boolean deleteAll() {
        Boolean res = Boolean.TRUE;
        try {
            bigliettoRepository.deleteAll();
        } catch (Exception e) {
            res = Boolean.FALSE;
        }
        return res;
    }

    @Override
    public boolean deleteById(int id) {
        Boolean res = Boolean.FALSE;
        Optional<Biglietto> biglietto = getById(id);
        if (biglietto.isPresent()) {
            try {
                bigliettoRepository.delete(biglietto.get());
                res = Boolean.TRUE;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                res = Boolean.FALSE;
            }
        }
        return res;
    }
}
