package org.example.cinema.service.impl;

import org.example.cinema.model.Biglietto;
import org.example.cinema.model.GenericResponse;
import org.example.cinema.model.Spettatore;
import org.example.cinema.repository.BigliettoRepository;
import org.example.cinema.repository.SpettatoreRepository;
import org.example.cinema.service.SpettatoreService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SpettatoreServiceImpl implements SpettatoreService {
    private final SpettatoreRepository spettatoreRepository;
    private final BigliettoRepository bigliettoRepository;

    public SpettatoreServiceImpl(SpettatoreRepository spettatoreRepository, BigliettoRepository bigliettoRepository) {
        this.spettatoreRepository = spettatoreRepository;
        this.bigliettoRepository = bigliettoRepository;
    }

   /* @Override
    public float getSconto(Spettatore spettatore) {
        if (spettatore.getAge() >= 70) {
            return spettatore.getBiglietto().riduzioneAnziani();
        } else if (spettatore.getAge() <= 5) {
            return spettatore.getBiglietto().riduzioneBambini();
        }
        return spettatore.getBiglietto().getPrezzo();
    }*/
    @Override
    public float getSconto(Spettatore s) {
        Optional<Spettatore> spettatore=getById(s.getId());
        if(spettatore.isPresent()) {
            if (spettatore.get().getAge() >= 70) {
                return spettatore.get().getBiglietto().riduzioneAnziani();
            } else if (spettatore.get().getAge() <= 5) {
                return spettatore.get().getBiglietto().riduzioneBambini();
            }
        }else{
            throw new RuntimeException("Spettatore non presente");
        }
        return spettatore.get().getBiglietto().getPrezzo();
    }

    @Override
    public Optional<Spettatore> getById(int id) {
        return spettatoreRepository.findById(id);
    }

    @Override
    public List<Spettatore> getAll() {
        return spettatoreRepository.findAll();
    }

    @Override
    public GenericResponse<Spettatore> insert(String nome, String cognome, LocalDate dataDiNascita, int idBiglietto) {
        GenericResponse<Spettatore> response = new GenericResponse<>();
        Optional<Biglietto> biglietto = bigliettoRepository.findById(idBiglietto);
        if (biglietto.isPresent()) {
            try {
                Spettatore spettatore = Spettatore.builder()
                        .nome(nome)
                        .cognome(cognome)
                        .dataDiNascita(dataDiNascita)
                        .build();
                spettatoreRepository.save(spettatore);
                response.setBody(spettatore);
            } catch (Exception e) {
                response.setErrorMessage(e.getMessage());
            }
        } else {
            response.setErrorMessage("Biglietto con id " + idBiglietto + " non trovato");
        }
        return response;

    }


    @Override
    public boolean deleteAll() {
        Boolean res = Boolean.TRUE;
        try {
            spettatoreRepository.deleteAll();
        } catch (Exception e) {
            res = Boolean.FALSE;
        }
        return res;
    }

    @Override
    public boolean deleteById(int id) {
        Boolean res = Boolean.FALSE;
        Optional<Spettatore> spettatore = getById(id);
        if (spettatore.isPresent()) {
            try {
                spettatoreRepository.delete(spettatore.get());
                res = Boolean.TRUE;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                res = Boolean.FALSE;
            }
        }
        return res;
    }
}
