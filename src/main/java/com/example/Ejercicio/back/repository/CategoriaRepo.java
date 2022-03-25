package com.example.Ejercicio.back.repository;

import com.example.Ejercicio.back.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepo extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findCategoriaById(Long id);

    void deleteCategoriaById(Long id);
}
