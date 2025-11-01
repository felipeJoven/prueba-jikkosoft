package com.api.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase base que proporciona atributos comunes para todas las entidades del sistema.
 * Contiene un identificador único, junto con las fechas de creación y actualización.
 * <p>
 *
 * Las demás entidades se heredan de esta clase para indicar que sus atributos
 * se incluirán en las tablas hijas, pero sin crear una tabla propia.
 * <p>
 *
 * Implementa de Serializable para permitir la serialización de las entidades.
 *
 * @author Felipe
 * @version 1.0
 */
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreatedDate
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDate fechaCreacion;

    @LastModifiedDate
    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;
}
