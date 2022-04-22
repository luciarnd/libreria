package com.example.Ejercicio.back.resource;

import com.example.Ejercicio.back.dto.LibroDTO;
import com.example.Ejercicio.back.model.Categoria;
import com.example.Ejercicio.back.model.Libro;
import com.example.Ejercicio.back.services.CategoriaServices;
import com.example.Ejercicio.back.services.LibroServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaResource{
    private final CategoriaServices categoriaServices;
    private final LibroServices libroServices;
    private final LibroResource libroResource;

    public CategoriaResource(CategoriaServices categoriaServices, LibroServices libroServices, LibroResource libroResource) {
        this.categoriaServices = categoriaServices;
        this.libroServices=libroServices;
        this.libroResource=libroResource;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Categoria>> getAllCategoriasToList(){
        List<Categoria> categorias = categoriaServices.findAllCategorias();
        List<Categoria> categoriasToList = new ArrayList<>();
        for (Categoria categoria : categorias) {
            if (categoria.getId() != 1) {
                categoriasToList.add(categoria);
            }
        }
        return new ResponseEntity<>(categoriasToList, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Categoria> getCategoriasById(@PathVariable("id") Long id){
        Categoria categoria = categoriaServices.findCategoriaById(id);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria categoria){
        Categoria newCategoria = categoriaServices.addCategoria(categoria);
        return new ResponseEntity<>(newCategoria, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria){
        Categoria updateCategoria = categoriaServices.updateCategoria(categoria);
        return new ResponseEntity<>(updateCategoria, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Categoria> deleteCategoria(@PathVariable("id") Long id){
        List<Libro> libros = libroServices.findAllLibros();
        for(int i=0; i < libros.size(); i++) {
            LibroDTO dto = new LibroDTO();
            dto.setId(libros.get(i).getId());
            dto.setTitulo(libros.get(i).getTitulo());
            dto.setEdicion(libros.get(i).getEdicion());
            if(libros.get(i).getAutor() != null) {
                dto.setAutor(libros.get(i).getAutor().getNombre() + " " + libros.get(i).getAutor().getApellido1());
            }
            dto.setCategoria(libros.get(i).getCategoria().getDescripcion());

            if(dto.getCategoria().equals(categoriaServices.findCategoriaById(id).getDescripcion())) {
                dto.setCategoria("Sin categoria");
                libroResource.updateLibro(dto);

            }
        }
        categoriaServices.deleteCategoria(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
