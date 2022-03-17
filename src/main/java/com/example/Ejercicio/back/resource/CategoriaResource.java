package com.example.Ejercicio.back.resource;

import com.example.Ejercicio.back.model.Categoria;
import com.example.Ejercicio.back.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaResource {
    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaResource (CategoriaService categoriaService) {
        this.categoriaService=categoriaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.findAllCategoria();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable("id") int id) {
        Categoria categoria = categoriaService.findCategoriaById(id);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Categoria> addCategoria (@RequestBody Categoria categoria) {
        Categoria newCategoria = categoriaService.addCategoria(categoria);
        return new ResponseEntity<>(newCategoria, HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<Categoria> updateCategoria (@RequestBody Categoria categoria) {
        Categoria editCategoria = categoriaService.updateCategoria(categoria);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoriaById (@PathVariable("id") int id) {
        categoriaService.deleteCategoriaById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
