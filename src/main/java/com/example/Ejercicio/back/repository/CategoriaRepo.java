package com.example.Ejercicio.back.repository;

import com.example.Ejercicio.back.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findCategoriaById(int id);

    void deleteCategoriaById(int id);
}
