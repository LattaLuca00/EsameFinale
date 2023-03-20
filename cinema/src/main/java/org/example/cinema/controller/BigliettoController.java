package org.example.cinema.controller;

import org.example.cinema.model.Biglietto;
import org.example.cinema.service.BigliettoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/biglietto")
public class BigliettoController {

    private final BigliettoService bigliettoService;

    public BigliettoController(BigliettoService bigliettoService) {
        this.bigliettoService = bigliettoService;
    }

    @GetMapping("/{id}")
    public Optional<Biglietto> getByid(@PathVariable int id) {
        return bigliettoService.getById(id);
    }

    @PostMapping()
    public Biglietto insert(@RequestBody Biglietto biglietto) {
        return bigliettoService.insert(biglietto.getPostoASedere(), biglietto.getPrezzo());
    }

    @GetMapping()
    public List<Biglietto> getAll() {
        return bigliettoService.getAll();
    }


    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable int id) {
        return bigliettoService.deleteById(id);
    }

    @DeleteMapping()
    public boolean deleteAll() {
        return bigliettoService.deleteAll();
    }
}
