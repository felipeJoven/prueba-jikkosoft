package com.api.funcion.controller;

import com.api.funcion.dto.SumaRequestDto;
import com.api.funcion.dto.SumaResponseDto;
import com.api.funcion.service.SumaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/suma")
public class SumaController {
    
    @Autowired
    private SumaService sumaService;

    @PostMapping
    public ResponseEntity<SumaResponseDto> calcular(@RequestBody SumaRequestDto request) {

        SumaResponseDto result = sumaService.encontrarSumaDos(request.getNumeros(), request.getObjetivo());

        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }
    
}
