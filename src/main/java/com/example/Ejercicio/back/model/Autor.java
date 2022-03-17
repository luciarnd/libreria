package com.example.Ejercicio.back.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Autor {
    @Id
    @Column(nullable = false)
    private String dni;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido1;
    private String apellido2;
    private String telefono;
    @Email
    private String email;
}
