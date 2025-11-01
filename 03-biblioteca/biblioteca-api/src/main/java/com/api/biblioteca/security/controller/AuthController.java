package com.api.biblioteca.security.controller;

import com.api.biblioteca.security.dto.AuthResponseDto;
import com.api.biblioteca.security.dto.LoginDto;
import com.api.biblioteca.security.dto.RegistroDto;
import com.api.biblioteca.security.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controlador para manejar solicitudes relacionadas con la autenticación.
 * Proporciona endpoints para el inicio de sesión y el registro de usuarios.
 *
 * @author Felipe Joven
 * @version 1.0
 */
@RestController
@RequestMapping("security")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    /**
     * Endpoint para el inicio de sesión de usuarios.
     *
     * @param request la solicitud de inicio de sesión que contiene las credenciales del usuario
     * @return una entidad de respuesta que contiene la respuesta de autenticación
     */
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto request){
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Endpoint para el registro de usuarios.
     *
     * @param request la solicitud de registro que contiene los detalles del usuario
     * @return una entidad de respuesta que contiene la respuesta de autenticación
     */
    @PostMapping(value = "/registrar")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegistroDto request){
        return ResponseEntity.ok(authService.register(request));
    }
}
