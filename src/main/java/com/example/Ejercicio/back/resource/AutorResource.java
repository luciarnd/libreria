package com.example.Ejercicio.back.resource;

import com.example.Ejercicio.back.dto.LibroDTO;
import com.example.Ejercicio.back.model.Autor;
import com.example.Ejercicio.back.model.Libro;
import com.example.Ejercicio.back.services.AutorServices;
import com.example.Ejercicio.back.services.LibroServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Component
@RestController
@RequestMapping("/autor")
public class AutorResource {
    private final AutorServices autorServices;
    private final LibroServices libroServices;
    private final LibroResource libroResource;

    public AutorResource(AutorServices autorServices, LibroServices libroServices, LibroResource libroResource) {
        this.autorServices = autorServices;
        this.libroServices=libroServices;
        this.libroResource=libroResource;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Autor>> getAllAutors(){
        List<Autor> autors = autorServices.findAllAutors();
        for (int i = 0; i<autors.size(); i++) {
            if (autors.get(i).getEmail() == null) {
                autors.get(i).setEmail("Desconocido");
            }
            if (autors.get(i).getTelefono() == null) {
                autors.get(i).setTelefono("Desconocido");
            }
        }
        return new ResponseEntity<>(autors, HttpStatus.OK);
    }
    @GetMapping("/getById/{dni}")
    public ResponseEntity<Autor> getAutorsById(@PathVariable("dni") String dni){
        Autor autor = autorServices.findAutorByDni(dni);
        return new ResponseEntity<>(autor, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Autor> addAutor(@RequestBody Autor autor){
        Autor newAutor = autorServices.addAutor(autor);
        return new ResponseEntity<>(newAutor, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Autor> updateAutor(@RequestBody Autor autor){
        Autor updateAutor = autorServices.updateAutor(autor);
        return new ResponseEntity<>(updateAutor, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{dni}")
    public ResponseEntity<Autor> deleteAutor(@PathVariable("dni") String dni){
        List<Libro> listaLibrosAutor = libroServices.findLibroByAutorId(dni);
        if(!listaLibrosAutor.isEmpty()) {
            for (int i = 0; i < listaLibrosAutor.size(); i++) {
                LibroDTO dto = new LibroDTO();
                dto.setId(listaLibrosAutor.get(i).getId());
                dto.setTitulo(listaLibrosAutor.get(i).getTitulo());
                dto.setEdicion(listaLibrosAutor.get(i).getEdicion());
                dto.setAutor("Desconocido");
                dto.setCategoria(listaLibrosAutor.get(i).getCategoria().getDescripcion());
                libroResource.updateLibro(dto);
            }
        }
        autorServices.deleteAutor(dni);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
