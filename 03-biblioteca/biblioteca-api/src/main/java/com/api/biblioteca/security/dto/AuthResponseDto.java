package com.api.biblioteca.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {

    String usuario;
    String rol;
    String token;
}
