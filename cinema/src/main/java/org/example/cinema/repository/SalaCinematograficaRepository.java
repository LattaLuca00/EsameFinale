package org.example.cinema.repository;

import org.example.cinema.model.SalaCinematografica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaCinematograficaRepository extends JpaRepository<SalaCinematografica, Integer> {
}
