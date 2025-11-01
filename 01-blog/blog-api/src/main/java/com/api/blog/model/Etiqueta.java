package com.api.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa una etiqueta en el blog.
 * Contiene información del nombre de la etiqueta.
 *
 * @author Felipe Joven
 * @version 1.0
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Etiqueta extends Base {

    @Column(unique = true, nullable = false)
    private String nombre;
}
