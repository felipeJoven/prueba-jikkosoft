package com.api.biblioteca.security.service.impl;

import com.api.biblioteca.exception.ConflictException;
import com.api.biblioteca.security.dto.UsuarioRequestDto;
import com.api.biblioteca.exception.NotFoundException;
import com.api.biblioteca.security.dto.UsuarioResponseDto;
import com.api.biblioteca.security.model.Rol;
import com.api.biblioteca.security.model.Usuario;
import com.api.biblioteca.security.repository.RolRepository;
import com.api.biblioteca.security.repository.UsuarioRepository;
import com.api.biblioteca.security.service.UsuarioService;
import com.api.biblioteca.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioResponseDto> listarUsuarios() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "usuarios");
        }

        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioResponseDto> listarUsuarioPorId(Integer id) {

        Optional<Usuario> usuarioId = usuarioRepository.findById(id);

        if (usuarioId.isEmpty()) {
            throw new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id);
        }

        return usuarioId.map(usuario -> modelMapper.map(usuario, UsuarioResponseDto.class));
    }

    @Override
    public UsuarioResponseDto agregarUsuario(UsuarioRequestDto usuarioNuevo) {

        boolean existeCorreo = usuarioRepository.existsByCorreo(usuarioNuevo.getCorreo());

        if (existeCorreo) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el correo");
        }

        Rol rolNuevo = rolRepository.findById(usuarioNuevo.getRolId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + usuarioNuevo.getRolId()));

        Usuario usuario = modelMapper.map(usuarioNuevo, Usuario.class);

        usuario.setId(null);
        usuario.setNombre(usuarioNuevo.getNombre());
        usuario.setApellido(usuarioNuevo.getApellido());
        usuario.setUsuario(usuarioNuevo.getUsuario());
        usuario.setCorreo(usuarioNuevo.getCorreo());
        usuario.setClave(passwordEncoder.encode(usuarioNuevo.getClave()));
        usuario.setRol(rolNuevo);
        usuario.setFechaCreacion(LocalDate.now());
        usuario.setFechaActualizacion(LocalDate.now());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        UsuarioResponseDto responseDto = modelMapper.map(usuarioGuardado, UsuarioResponseDto.class);
        responseDto.setRolId(rolNuevo.getId());
        responseDto.setRol(rolNuevo.getRol());

        return responseDto;
    }
/*
    @Override
    @Transactional
    public Usuario actualizarUsuario(Integer id, Usuario usuario) {
        return null;
        *//*

        Usuario usuarioActualizado = usuarioRepository.findById(id).
                orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        UsuarioDto usuarioDto = new UsuarioDto();

        boolean existeCorreo = usuarioRepository.existsByEmail(usuarioDto.getCorreo());

        String correo = usuarioDto.getCorreo();
        if (correo.isEmpty()) {
//            throw new BadRequestException(Message.MENSAJE_ERROR_CORREO_VACIO);
        }

        if (!usuarioActualizado.getCorreo().equals(usuario.getCorreo()) && existeCorreo) {
//            throw new BadRequestException(Message.MENSAJE_ERROR_CORREO);
        }

        String telefono = String.valueOf(usuarioDto.getTelefono());

        if (!telefono.equals(usuario.getTelefono()) && telefono.length() != 10) {
//            throw new BadRequestException(Message.MENSAJE_ERROR_TELEFONO);
        }

//        if (passwordsEqual(usuarioDto.getPassword(), usuarioDto.getConfirmPassword())) {
//            throw new BadRequestException(Message.MENSAJE_ERROR_PASSWORD);
//        }

        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setUsuario(usuarioDto.getUsuario());
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setTelefono(telefono);
        usuario.setFechaCreacion(LocalDate.now());
//        usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        // Verificar que existan los roles en la bd
//                Rol rolActualizado = rolRepository.findById(usuarioDto.getRolId())
//                        .orElseThrow(() -> new EntityNotFoundException(Message.MENSAJE_ERROR_ROL));
//                usuario.setRol(rolActualizado);
//                usuarioRepository.save(usuario);
        return usuario;*//*
    }*/

    @Override
    @Transactional
    public void eliminarUsuario(Integer id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        usuarioRepository.delete(usuario);
    }
}
