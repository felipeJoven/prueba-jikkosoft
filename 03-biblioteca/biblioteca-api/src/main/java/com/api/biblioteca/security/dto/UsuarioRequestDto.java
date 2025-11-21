package com.api.biblioteca.security.dto;

import lombok.Data;

@Data
public class UsuarioRequestDto {

    private String nombre;
    private String apellido;
    private String usuario;
    private String correo;
    private String clave;
    private Integer rolId;
}
