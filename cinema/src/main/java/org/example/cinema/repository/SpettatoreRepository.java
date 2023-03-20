package org.example.cinema.repository;

import org.example.cinema.model.Spettatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpettatoreRepository extends JpaRepository<Spettatore, Integer> {
}
