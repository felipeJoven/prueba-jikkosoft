package com.api.biblioteca.security.dto;

import lombok.Data;

@Data
public class UsuarioResponseDto {

    private Integer id;
    private String nombre;
    private String usuario;
    private String correo;
    private Integer rolId;
    private String rol;
}
