package com.api.biblioteca.security.dto;

import lombok.Data;

@Data
public class RegistroDto {

    String nombre;
    String usuario;
    String correo;
    String clave;
}
