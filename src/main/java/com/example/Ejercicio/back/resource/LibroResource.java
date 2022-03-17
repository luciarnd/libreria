package com.example.Ejercicio.back.resource;

import com.example.Ejercicio.back.model.Libro;
import com.example.Ejercicio.back.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro")
public class LibroResource {
    private final LibroService libroService;

    @Autowired
    public LibroResource (LibroService libroService) {
        this.libroService=libroService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Libro>> getAllLibros () {
        List<Libro> libros = libroService.findAllLibro();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable("id") int id) {
        Libro libro = libroService.findLibroById(id);
        return new ResponseEntity<>(libro, HttpStatus.OK);
    }

    @GetMapping("/autor/{dni}")
    public ResponseEntity<List<Libro>> getLibroByAutorDni(@PathVariable ("dni") String dni) {
        List<Libro> libros = libroService.findLibroByAutorDni(dni);
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Libro>> getLibroByCategoriaId(@PathVariable("id") int id) {
        List<Libro> libros = libroService.findLibroByCategoriaId(id);
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Libro> addLibro (@RequestBody Libro libro) {
        Libro newLibro = libroService.addLibro(libro);
        return new ResponseEntity<>(newLibro, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Libro> updateLibro (@PathVariable("id") int id, @RequestBody Libro libro) {
        Libro editLibro = libroService.updateLibro(libro, id);
        return new ResponseEntity<>(libro, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Libro> deleteLibro (@PathVariable("id") int id) {
        libroService.deleteLibroById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
