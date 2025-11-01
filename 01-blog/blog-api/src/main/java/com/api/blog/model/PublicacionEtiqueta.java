package com.api.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad intermedia que representa la relación entre una publicación y una etiqueta.
 * Un usuario puede tener varios roles y un rol puede pertenecer a varios usuarios.
 * <p>
 *
 * Esta tabla pivote permite validar que en una publicación puede tener muchas etiquetas
 * y una etiqueta puede estar en muchas publicaciones.
 *
 * @author Felipe
 * @version 1.0
 */
@Entity
@Table(name = "publicacion_etiqueta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionEtiqueta extends Base {

    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "etiqueta_id", nullable = false)
    private Etiqueta etiqueta;
}
