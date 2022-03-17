package com.example.Ejercicio.back.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    @Id
    @Column(name = "id", nullable = false)
    @NotNull
    private int id;
    @NotNull
    @Lob
    private String descripcion;

}
