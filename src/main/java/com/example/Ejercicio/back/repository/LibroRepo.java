package com.example.Ejercicio.back.repository;

import com.example.Ejercicio.back.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepo extends JpaRepository<Libro, Integer> {
}
