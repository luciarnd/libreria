package com.example.Ejercicio.back.repository;

import com.example.Ejercicio.back.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepo extends JpaRepository<Libro, Integer> {
    void deleteLibroById(int id);

    Optional<Libro> findLibroById(int id);

    List<Libro> findLibroByAutorId(String dni);

    List<Libro> findLibroByCategoriaId(int id);
}
