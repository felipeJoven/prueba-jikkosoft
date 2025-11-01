package com.api.biblioteca.security.service.impl;

import com.api.biblioteca.exception.ConflictException;
import com.api.biblioteca.security.dto.AuthResponseDto;
import com.api.biblioteca.security.dto.LoginDto;
import com.api.biblioteca.security.dto.RegistroDto;
import com.api.biblioteca.security.model.Rol;
import com.api.biblioteca.security.model.Usuario;
import com.api.biblioteca.security.repository.RolRepository;
import com.api.biblioteca.security.repository.UsuarioRepository;
import com.api.biblioteca.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Servicio para la autenticación y registro de usuarios.
 * <p>
 * Utiliza {@link UsuarioRepository} para acceder a la informacion y ajsutes del repositorio.
 * Utiliza {@link JwtService} para acceder a los servicios de JWT.
 * Utiliza {@link PasswordEncoder} para acceder a los servicios de encriptacion de comparacion de contraseña.
 * Utiliza {@link AuthenticationManager} para acceder a los servicios de manejo de sesion de spring security.
 *
 * @author Felipe Joven
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Autentica a un usuario y genera un token JWT.
     *
     * @param request el objeto LoginDto que contiene las credenciales del usuario.
     * @return un AuthResponseDto con el token JWT.
     */
    public AuthResponseDto login(LoginDto request) {
        // Autenticación con Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getClave())
        );

        // Cargar usuario
        UserDetails user = (UserDetails) usuarioRepository
                .findByCorreo(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario"));

        // Generar token JWT
        String token = jwtService.getToken((Usuario) user);
        return AuthResponseDto.builder()
                .usuario(((Usuario) user).getUsuario())
                .rol(((Usuario) user).getRol().getRol())
                .token(token)
                .build();
    }

    /**
     * Registra a un nuevo usuario y genera un token JWT.
     *
     * @param request el objeto RegistroDto que contiene los datos del nuevo usuario.
     * @return un AuthResponseDto con el token JWT.
     */
    public AuthResponseDto register(RegistroDto request) {
        Rol rolAsignado;

        if (usuarioRepository.count() == 0) {
            rolAsignado = rolRepository.findByRol("Administrador")
                    .orElseThrow(() -> new RuntimeException("Rol Administrador no encontrado"));
        } else {
            throw new ConflictException(MessageUtils.YA_EXISTE, "un admin");
        }

        Usuario user = Usuario.builder()
                .nombre(request.getNombre())
                .usuario(request.getUsuario())
                .clave(passwordEncoder.encode(request.getClave()))
                .correo(request.getCorreo())
                .rol(rolAsignado)
                .build();

        usuarioRepository.save(user);

        String token = jwtService.getToken(user);
        return AuthResponseDto.builder()
                .token(token)
                .build();
    }
}
