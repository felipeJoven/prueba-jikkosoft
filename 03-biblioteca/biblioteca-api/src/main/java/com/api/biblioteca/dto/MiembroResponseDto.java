package com.api.biblioteca.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MiembroResponseDto {

    private Integer id;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;
    private String nombre;
    private String apellido;
    private String numeroIdentificacion;
    private String telefono;
    private boolean activo;
    private Integer tipoIdentificacionId;
    private String tipoIdentificacion;
    private Integer bibliotecaId;
    private String nombreBiblioteca;
}
