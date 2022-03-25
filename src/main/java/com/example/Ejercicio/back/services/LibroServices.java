package com.example.Ejercicio.back.services;

import com.example.Ejercicio.back.model.Libro;
import com.example.Ejercicio.back.repository.LibroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class LibroServices {
    private LibroRepo libroRepo;
    private final String error = "No se ha encontrado al Libro";

    @Autowired
    public LibroServices(LibroRepo libroRepo) {
        this.libroRepo = libroRepo;
    }

    public Libro addLibro(Libro libro){
        return libroRepo.save(libro);
    }

    public List<Libro> findAllLibros(){
        return libroRepo.findAll();
    }

    public Libro findLibroById(Long id){
        return libroRepo.findLibroById(id).orElseThrow(() -> new IllegalArgumentException(error));
    }

    public void deleteLibro(Long id){
        libroRepo.deleteLibroById(id);
    }

    public Libro updateLibro(Libro libro){
        return libroRepo.save(libro);
    }

    public List<Libro> findLibroByAutorId(String dni){
        return libroRepo.findLibroByAutorDni(dni);
    }

    public List<Libro> findLibroByCategoriaId(Long id){
        return libroRepo.findLibroByCategoriaId(id);
    }
}

