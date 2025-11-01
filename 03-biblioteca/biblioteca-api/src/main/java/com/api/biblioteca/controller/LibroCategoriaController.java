package com.api.biblioteca.controller;

import com.api.biblioteca.model.Libro;
import com.api.biblioteca.service.LibroCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("libros")
@CrossOrigin("*")
@RequiredArgsConstructor
public class LibroCategoriaController {

    private final LibroCategoriaService libroCategoriaService;

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Libro>> obtenerLibrosPorCategoria(@PathVariable Integer id) {

        List<Libro> libros = libroCategoriaService.listarLibrosPorCategoria(id);
        return ResponseEntity.ok(libros);
    }
}
