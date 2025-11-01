package com.api.blog.controller;

import com.api.blog.service.EtiquetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("etiqueta")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EtiquetaController {

    private final EtiquetaService etiquetaService;
}
