package com.api.biblioteca.controller;

import com.api.biblioteca.service.RecuperarClaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recuperar-clave")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RecuperarClaveController {

    private final RecuperarClaveService recuperarClaveService;
}
