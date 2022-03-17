package com.example.Ejercicio.back.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Autor implements Serializable {
    @Getter
    @Setter
    @Id
    @NotNull
    private String dni;
    @Getter
    @Setter
    @NotNull
    private String nombre;
    @Getter
    @Setter
    @NotNull
    private String apellido1;
    @Getter
    @Setter
    private String apellido2;
    @Getter
    @Setter
    private String telefono;
    @Getter
    @Setter
    @Email
    private String email;
}