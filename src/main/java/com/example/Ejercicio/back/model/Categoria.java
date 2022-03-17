package com.example.Ejercicio.back.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Categoria implements Serializable {
    @Getter
    @Setter
    @Id
    @NotNull
    private int id;
    @Getter
    @Setter
    private String descripcion;
}

