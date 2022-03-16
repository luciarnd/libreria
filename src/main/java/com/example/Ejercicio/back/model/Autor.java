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
public class Autor {


    private int id;
    @Id
    @Column(nullable = false)
    private String dni;
    @NonNull
    private String nombre;
    @NonNull
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String email;
}
