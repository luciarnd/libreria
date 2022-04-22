package com.example.Ejercicio.back.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Mail implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    @Setter
    @NotNull
    private String destinatario;

    @Getter
    @Setter
    private String asunto;

    @Getter
    @Setter
    private String mensaje;


}
