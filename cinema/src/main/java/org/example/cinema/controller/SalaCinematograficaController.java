package org.example.cinema.controller;

import org.example.cinema.model.GenericResponse;
import org.example.cinema.service.SalaCinematograficaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sala")
public class SalaCinematograficaController {
    private final SalaCinematograficaService cinematograficaService;

    public SalaCinematograficaController(SalaCinematograficaService cinematograficaService) {
        this.cinematograficaService = cinematograficaService;
    }

    @DeleteMapping()
    public boolean deleteAll() {
        return cinematograficaService.svuotaSala();
    }

    @GetMapping("/{id}")
    public GenericResponse<Float> getIncasso(@PathVariable int id) {
        return cinematograficaService.calcolaIncasso(id);
    }


}
