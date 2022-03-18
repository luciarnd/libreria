package com.example.Ejercicio.back.services;

import com.example.Ejercicio.back.model.Libro;
import com.example.Ejercicio.back.repository.LibroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LibroService {


        private final LibroRepo libroRepo;
        private final String error = "No se ha encontrado el libro";

        @Autowired
        public LibroService(LibroRepo LibroRepo) {
            this.libroRepo=LibroRepo;
        }


        public Libro addLibro (Libro libro) {
            return libroRepo.save(libro);
        }

        public Libro findLibroById(int id) {
            return libroRepo.findLibroById(id).orElseThrow(() -> new IllegalArgumentException(error));
        }

        public List<Libro> findAllLibro () {
            return libroRepo.findAll();
        }

        public Libro updateLibro (Libro libro, int id) {
            if(id == findLibroById(id).getId()) {
                libro.setId(id);
                return libroRepo.save(libro);
            } else {
                throw new IllegalArgumentException(error);
            }
        }

        public void deleteLibroById(int id) {
            libroRepo.deleteLibroById(id);
        }

        public List<Libro> findLibroByAutorDni(String dni) {
            return libroRepo.findLibroByAutorDni(dni);
        }

        public List<Libro> findLibroByCategoriaId(int id) {
            return libroRepo.findLibroByCategoriaId(id);
        }

}
