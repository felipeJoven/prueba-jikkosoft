package com.api.biblioteca.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrestamoDto {

    private Integer id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean devuelto;
    private String libroIsbn;
    private String miembroNumeroIdentificacion;
}
