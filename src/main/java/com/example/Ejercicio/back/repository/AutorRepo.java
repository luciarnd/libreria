package com.example.Ejercicio.back.repository;

import com.example.Ejercicio.back.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepo extends JpaRepository<Autor, String> {

    Optional<Autor> findAutorByDni(String dni);

    void deleteAutorByDni(String dni);
}
