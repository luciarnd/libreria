package com.example.Ejercicio.back.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Libro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;
    private String titulo;
    @NotNull
    private int edicion;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "autor_dni")
    private Autor autor;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
