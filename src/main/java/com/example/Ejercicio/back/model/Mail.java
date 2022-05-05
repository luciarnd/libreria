package com.example.Ejercicio.back.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mail implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String destinatario;

    private String asunto;

    private String mensaje;


}
