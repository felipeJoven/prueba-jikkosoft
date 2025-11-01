package com.api.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa un comentario dentro de una publicación del blog.
 * Contiene información del contenido del comentario.
 * <p>
 *
 * Esta tabla está asociada con una relación de muchos a uno con Publicacion y Usuario.
 * Cada comentario está asociado publicacion y es creado por un usuario.
 *
 * @author Felipe Joven
 * @version 1.0
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comentario extends Base {

    @Column(nullable = false)
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
