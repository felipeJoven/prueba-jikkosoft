package com.api.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa a un rol en el sistema.
 * Contiene información del nombre de los roles.
 *
 * @author Felipe Joven
 * @version 1.0
 */
@Entity
@Table(name = "rol")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rol extends Base {

    @Column(unique = true, nullable = false)
    private String rol;
}
