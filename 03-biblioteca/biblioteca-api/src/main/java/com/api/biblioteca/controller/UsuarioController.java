package com.api.biblioteca.controller;

import com.api.biblioteca.security.dto.UsuarioRequestDto;
import com.api.biblioteca.security.dto.UsuarioResponseDto;
import com.api.biblioteca.security.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuario")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<UsuarioResponseDto>> obtenerUsuarios() {

        List<UsuarioResponseDto> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<Optional<UsuarioResponseDto>> obtenerUsuarioPorId(@PathVariable Integer id) {

        Optional<UsuarioResponseDto> usuarioId = usuarioService.listarUsuarioPorId(id);
        return ResponseEntity.ok(usuarioId);
    }


    @PostMapping("")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UsuarioResponseDto> crearUsuario(@RequestBody UsuarioRequestDto requestDto) {

        UsuarioResponseDto nuevoUsuario = usuarioService.agregarUsuario(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    /*@PutMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UsuarioResponseDto> editarUsuario(@PathVariable Integer id, @RequestBody UsuarioRequestDto requestDto) {

        UsuarioResponseDto usuarioActualizado = usuarioService.actualizarUsuario(id, requestDto);
        return ResponseEntity.ok(usuarioActualizado);
    }*/

    @DeleteMapping("/{id}")
    @PreAuthorize(("hasRole('Administrador')"))
    public ResponseEntity<?> borrarUsuario(@PathVariable Integer id) {

        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
