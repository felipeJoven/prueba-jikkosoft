package com.api.blog.controller;

import com.api.blog.service.UsuarioRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario-rol")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UsuarioRolController {

    private final UsuarioRolService usuarioRolService;
}
