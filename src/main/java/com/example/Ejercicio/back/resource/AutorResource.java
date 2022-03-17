package com.example.Ejercicio.back.resource;


import com.example.Ejercicio.back.services.AutorService;
import com.example.Ejercicio.back.model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/autor")
public class AutorResource {

    private final AutorService autorService;

    @Autowired
    public AutorResource(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Autor>> getAllAutor () {
        List<Autor> autors = autorService.findAllAutor();
        return new ResponseEntity<>(autors, HttpStatus.OK);
    }

    @GetMapping("/getById/{dni}")
    public ResponseEntity<Autor> getAutorByDni(@PathVariable("dni") String dni) {
        Autor autor = autorService.findAutorByDni(dni);
        return new ResponseEntity<>(autor, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Autor> addAutor(@RequestBody Autor autor) {
        Autor newAutor = autorService.addAutor(autor);
        return new ResponseEntity<>(newAutor, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{dni}")
    public ResponseEntity<Autor> updateAutor(@PathVariable("dni") String dni, @RequestBody Autor autor) {
        Autor editAutor = autorService.updateAutor(autor, dni);
        return new ResponseEntity<>(editAutor, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{dni}")
    public ResponseEntity<Autor> deleteAutor(@PathVariable("dni") String dni) {
        autorService.deleteAutorByDni(dni);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
