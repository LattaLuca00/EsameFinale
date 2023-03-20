package org.example.cinema.controller;

import org.example.cinema.model.Film;
import org.example.cinema.model.GenericResponse;
import org.example.cinema.model.Spettatore;
import org.example.cinema.service.SpettatoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spettatore")
public class SpettatoreController {
    private final SpettatoreService spettatoreService;

    public SpettatoreController(SpettatoreService spettatoreService) {
        this.spettatoreService = spettatoreService;
    }

    @GetMapping("/{id}")
    public Optional<Spettatore> getByid(@PathVariable int id) {
        return spettatoreService.getById(id);
    }

    @PostMapping()
    public GenericResponse<Spettatore> insert(@RequestBody Spettatore spettatore) {
        return spettatoreService.insert(spettatore.getNome(), spettatore.getCognome(), spettatore.getDataDiNascita(), spettatore.getIdBiglietto());
    }

    @GetMapping()
    public List<Spettatore> getAll() {
        return spettatoreService.getAll();
    }


    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable int id) {
        return spettatoreService.deleteById(id);
    }

    @DeleteMapping()
    public boolean deleteAll() {
        return spettatoreService.deleteAll();
    }
}
