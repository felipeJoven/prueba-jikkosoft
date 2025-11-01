package com.api.biblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipo_identificacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoIdentificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_identificacion", unique = true, nullable = false)
    private String tipo;
}
