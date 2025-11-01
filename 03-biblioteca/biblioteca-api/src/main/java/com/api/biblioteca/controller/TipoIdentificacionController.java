package com.api.biblioteca.controller;

import com.api.biblioteca.model.TipoIdentificacion;
import com.api.biblioteca.service.TipoIdentificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tipo-identificacion")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TipoIdentificacionController {

    private final TipoIdentificacionService service;

    @GetMapping("")
    public ResponseEntity<?> obtenerTiposIdentificaciones() {

        List<TipoIdentificacion> tipos = service.listarTipos();
        return ResponseEntity.ok(tipos);
    }
}
