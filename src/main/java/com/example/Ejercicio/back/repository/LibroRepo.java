package com.example.Ejercicio.back.repository;

import com.example.Ejercicio.back.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepo extends JpaRepository<Libro, Long> {
    void deleteLibroById(Long id);

    Optional<Libro> findLibroById(Long id);

    List<Libro> findLibroByAutorDni(String dni);

    List<Libro> findLibroByCategoriaId(Long id);
}
