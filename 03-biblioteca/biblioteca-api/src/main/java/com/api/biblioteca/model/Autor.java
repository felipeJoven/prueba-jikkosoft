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
public class Autor extends Base {

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String nacionalidad;
}
