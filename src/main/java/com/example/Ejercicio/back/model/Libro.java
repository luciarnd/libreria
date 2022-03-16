package com.example.Ejercicio.back.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @NonNull
    private String titulo;
    @NonNull
    private int edicion;
    private int idAutor;
    @NonNull
    private int idCategoria;
}
