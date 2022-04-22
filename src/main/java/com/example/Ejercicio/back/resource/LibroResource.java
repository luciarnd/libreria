package com.example.Ejercicio.back.resource;

import com.example.Ejercicio.back.dto.LibroDTO;
import com.example.Ejercicio.back.model.Autor;
import com.example.Ejercicio.back.model.Libro;
import com.example.Ejercicio.back.services.AutorServices;
import com.example.Ejercicio.back.services.CategoriaServices;
import com.example.Ejercicio.back.services.LibroServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Component
@RestController
@RequestMapping("/libro")
public class LibroResource {


    @Autowired
    private final CategoriaServices categoriaServices;
    private final LibroServices libroServices;
    private final AutorServices autorServices;

    public LibroResource (LibroServices libroServices, AutorServices autorServices, CategoriaServices categoriaServices) {
        this.libroServices=libroServices;
        this.autorServices=autorServices;
        this.categoriaServices=categoriaServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LibroDTO>> getAllLibros(){
        List<Libro> libros = libroServices.findAllLibros();
        List<LibroDTO> libroAutorCategoria = new ArrayList<>();
        for (int i = 0; i<libros.size(); i++) {
            if (libros.get(i).getAutor() == null){
                libros.get(i).setAutor(new Autor());
            }
            libroAutorCategoria.add(new LibroDTO());
            libroAutorCategoria.get(i).setId(libros.get(i).getId());
            libroAutorCategoria.get(i).setTitulo(libros.get(i).getTitulo());
            libroAutorCategoria.get(i).setEdicion(libros.get(i).getEdicion());
            libroAutorCategoria.get(i).setAutor(libros.get(i).getAutor().getNombre() + " " + libros.get(i).getAutor().getApellido1());
            libroAutorCategoria.get(i).setCategoria(libros.get(i).getCategoria().getDescripcion());

}
        return new ResponseEntity<>(libroAutorCategoria, HttpStatus.OK);
    }
    @GetMapping("getById/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable("id") Long id){
        Libro libro = libroServices.findLibroById(id);
        return new ResponseEntity<>(libro, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Libro> addLibro(@RequestBody LibroDTO libro){
        Libro libroStr = new Libro();
        for (int i = 0; i< categoriaServices.findAllCategorias().size(); i++){
            if (categoriaServices.findAllCategorias().get(i).getDescripcion().equals(libro.getCategoria())){
                libroStr.setCategoria(categoriaServices.findAllCategorias().get(i));
            }
        }

        for (int j=0; j < autorServices.findAllAutors().size(); j++){
            String fullName= autorServices.findAllAutors().get(j).getNombre() + " " + autorServices.findAllAutors().get(j).getApellido1();

            if(fullName.equals(libro.getAutor())) {
                libroStr.setAutor(autorServices.findAllAutors().get(j));
            }
        }
        libroStr.setId(libro.getId());
        libroStr.setTitulo(libro.getTitulo());
        libroStr.setEdicion(libro.getEdicion());


        Libro newLibro = libroServices.addLibro(libroStr);
        return new ResponseEntity<>(newLibro, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Libro> updateLibro(@RequestBody LibroDTO libro){
        Libro libroStr = new Libro();
        for (int i = 0; i< categoriaServices.findAllCategorias().size(); i++){
            if (categoriaServices.findAllCategorias().get(i).getDescripcion().equals(libro.getCategoria())){
                libroStr.setCategoria(categoriaServices.findAllCategorias().get(i));
            }
        }

        for (int j=0; j < autorServices.findAllAutors().size(); j++){
            String fullName= autorServices.findAllAutors().get(j).getNombre() + " " + autorServices.findAllAutors().get(j).getApellido1();

            if(fullName.equals(libro.getAutor())) {
                libroStr.setAutor(autorServices.findAllAutors().get(j));
            }
        }
        libroStr.setId(libro.getId());
        libroStr.setTitulo(libro.getTitulo());
        libroStr.setEdicion(libro.getEdicion());
        Libro updateLibro = libroServices.updateLibro(libroStr);
        return new ResponseEntity<>(updateLibro, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Libro> deleteLibro(@PathVariable("id") Long id){
        libroServices.deleteLibro(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/autor/{dni}")
    public ResponseEntity<List<Libro>> getLibrosMismoAutor(@PathVariable("dni") String dni){
        List<Libro> libros = libroServices.findLibroByAutorId(dni);
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }
    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Libro>> getLibrosMismaCategoria(@PathVariable("id") Long id){
        List<Libro> libros = libroServices.findLibroByCategoriaId(id);
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

}
