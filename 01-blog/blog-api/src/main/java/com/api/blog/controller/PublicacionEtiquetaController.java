package com.api.blog.controller;

import com.api.blog.service.PublicacionEtiquetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("publicacion-etiqueta")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PublicacionEtiquetaController {

    private final PublicacionEtiquetaService publicacionEtiquetaService;
}
