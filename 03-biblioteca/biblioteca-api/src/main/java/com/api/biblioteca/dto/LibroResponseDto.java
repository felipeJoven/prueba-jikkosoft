package com.api.biblioteca.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LibroResponseDto {

    private Integer id;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;
    private String titulo;
    private String isbn;
    private boolean activo;
    private Integer autorId;
    private String nombreAutor;
}
