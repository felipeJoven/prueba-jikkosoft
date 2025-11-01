package com.api.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Miembro extends Base {

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(name = "numero_identificacion", unique = true, nullable = false)
    private String numeroIdentificacion;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "tipo_identificacion_id", nullable = false)
    private TipoIdentificacion tipoIdentificacion;

    @ManyToOne
    @JoinColumn(name = "biblioteca_id", nullable = false)
    private Biblioteca biblioteca;
}
