package org.example.cinema.service.impl;

import org.example.cinema.model.Spettatore;
import org.example.cinema.service.SpettatoreService;

public class SpettatoreServiceImpl implements SpettatoreService {
    @Override
    public float getSconto(Spettatore spettatore) {
        if (spettatore.getAge() >= 70) {
            return spettatore.getBiglietto().riduzioneAnziani();
        } else if (spettatore.getAge() <= 5) {
            return spettatore.getBiglietto().riduzioneBambini();
        }
        return spettatore.getBiglietto().getPrezzo();
    }
}
