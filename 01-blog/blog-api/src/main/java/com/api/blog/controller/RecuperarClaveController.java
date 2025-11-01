package com.api.blog.controller;

import com.api.blog.service.RecuperarClaveService;
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
