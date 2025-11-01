package com.api.biblioteca.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrestamoRequestDto {

    private LocalDate fechaDevolucion;
    private Integer libroId;
    private Integer miembroId;
    private boolean devuelto;
}
