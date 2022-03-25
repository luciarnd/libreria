package com.example.Ejercicio.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibroDTO {

    private Long id;
    private String titulo;
    private int edicion;
    private String autor;
    private String categoria;
}
