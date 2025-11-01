package com.api.biblioteca.security.service;

import com.api.biblioteca.security.dto.UsuarioRequestDto;
import com.api.biblioteca.security.dto.UsuarioResponseDto;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioResponseDto> listarUsuarios();
    Optional<UsuarioResponseDto> listarUsuarioPorId(Integer id);
    UsuarioResponseDto agregarUsuario(UsuarioRequestDto usuarioNuevo);
//    UsuarioResponseDto actualizarUsuario(Integer id, UsuarioRequestDto usuarioNuevo);
    void eliminarUsuario(Integer id);
}
