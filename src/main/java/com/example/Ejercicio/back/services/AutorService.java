package com.example.Ejercicio.back.services;

import com.example.Ejercicio.back.model.Autor;
import com.example.Ejercicio.back.repository.AutorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AutorService {

    private final AutorRepo autorRepo;

    @Autowired
    public AutorService(AutorRepo autorRepo) {
        this.autorRepo=autorRepo;
    }


    public Autor addAutor (Autor autor) {
        return autorRepo.save(autor);
    }

    public Autor findAutorByDni(String dni) {
        return autorRepo.findAutorByDni(dni).orElseThrow(() -> new IllegalArgumentException("Autor by dni " + dni + " was not found"));
    }

    public List<Autor> findAllAutor () {
        return autorRepo.findAll();
    }

    public Autor updateAutor (Autor autor, String dni) {
        if(dni == findAutorByDni(dni).getDni()) {
            autor.setDni(dni);
            return autorRepo.save(autor);
        } else {
            throw new IllegalArgumentException("Autor by dni " + dni + " was not found");
        }
    }

    public void deleteAutorByDni(String dni) {
        autorRepo.deleteAutorByDni(dni);
    }

}
