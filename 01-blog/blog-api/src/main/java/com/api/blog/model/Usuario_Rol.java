package com.api.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad intermedia que representa la relación entre un usuario y un rol.
 * Un usuario puede tener varios roles y un rol puede pertenecer a varios usuarios.
 * <p>
 *
 * Esta tabla pivote permite gestionar los permisos de forma flexible.
 *
 * @author Felipe
 * @version 1.0
 */
@Entity
@Table(name = "usuario_rol")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario_Rol extends Base {

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;
}
