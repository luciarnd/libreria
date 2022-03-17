package com.example.Ejercicio.back.services;


import com.example.Ejercicio.back.model.Categoria;
import com.example.Ejercicio.back.repository.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoriaService {

    private final CategoriaRepo categoriaRepo;
    private final String error ="No se encontro la categoria";

    @Autowired
    public CategoriaService(CategoriaRepo categoriaRepo) {
        this.categoriaRepo= categoriaRepo;
    }

    public List<Categoria> findAllCategoria () {
        return categoriaRepo.findAll();
    }

    public Categoria findCategoriaById(int id) {
        return categoriaRepo.findCategoriaById(id).orElseThrow(() -> new IllegalArgumentException(error));
    }

    public Categoria addCategoria (Categoria categoria) {
        return categoriaRepo.save(categoria);
    }

    public Categoria updateCategoria (Categoria categoria) {
        return categoriaRepo.save(categoria);
    }

    public void deleteCategoriaById(int id) {
        categoriaRepo.deleteCategoriaById(id);
    }
}
