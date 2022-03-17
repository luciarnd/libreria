package com.example.Ejercicio.back.repository;

import com.example.Ejercicio.back.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {
}
