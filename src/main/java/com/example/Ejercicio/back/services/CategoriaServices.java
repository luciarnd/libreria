package com.example.Ejercicio.back.services;

import com.example.Ejercicio.back.model.Categoria;
import com.example.Ejercicio.back.repository.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
    public class CategoriaServices{
        private CategoriaRepo categoriaRepo;
        private final String error = "No se ha encontrado al Categoria";

        @Autowired
        public CategoriaServices(CategoriaRepo categoriaRepo){
            this.categoriaRepo = categoriaRepo;
        }

        public Categoria addCategoria(Categoria categoria){
            return categoriaRepo.save(categoria);
        }

        public List<Categoria> findAllCategorias(){
            return categoriaRepo.findAll();
        }

        public Categoria findCategoriaById(Long id){
            return categoriaRepo.findCategoriaById(id).orElseThrow(() -> new IllegalArgumentException(error));
        }

        public void deleteCategoria(Long id){
            categoriaRepo.deleteCategoriaById(id);
        }

        public Categoria updateCategoria(Categoria categoria){
            return categoriaRepo.save(categoria);
        }
    }

