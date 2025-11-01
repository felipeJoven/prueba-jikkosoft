package com.api.biblioteca.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrestamoResponseDto {

    private Integer id;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;
    private Integer libroId;
    private String libro;
    private boolean devuelto;
    private Integer miembroId;
    private String miembro;
}
