package com.api.biblioteca.dto;

import lombok.Data;

@Data
public class MiembroRequestDto {

    private String nombre;
    private String apellido;
    private String numeroIdentificacion;
    private String telefono;
    private boolean activo;
    private Integer tipoIdentificacionId;
    private Integer bibliotecaId;
}
