package com.api.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa a un usuario en el sistema.
 * Contiene información básica como el nombre para el usuario, correo y contraseña.
 *
 * @author Felipe Joven
 * @version 1.0
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends Base {

    @Column(name = "nombre_usuario", unique = true, nullable = false)
    private String usuario;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String clave;
}
