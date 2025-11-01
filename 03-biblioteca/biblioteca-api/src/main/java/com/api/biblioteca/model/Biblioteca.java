package com.api.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Biblioteca extends Base {

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String telefono;
}
