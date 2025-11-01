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
 * Entidad que representa una publicación en el blog.
 * Contiene información como el titulo, contenido.
 * <p>
 * Esta entidad se relaciona muchos a uno con Usuario ya que un usuario puede tener
 * muchas publicaciones, pero una publicación solo puede tener un usuario.
 *
 * @author Felipe Joven
 * @version 1.0
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publicacion extends Base {

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
