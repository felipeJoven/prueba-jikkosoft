package com.api.blog.service.impl;

import com.api.blog.repository.UsuarioRepository;
import com.api.blog.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
}
