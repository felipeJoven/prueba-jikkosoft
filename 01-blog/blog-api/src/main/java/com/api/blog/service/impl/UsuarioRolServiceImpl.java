package com.api.blog.service.impl;

import com.api.blog.repository.UsuarioRolRepository;
import com.api.blog.service.UsuarioRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioRolServiceImpl implements UsuarioRolService {

    private final UsuarioRolRepository usuarioRolRepository;
}
