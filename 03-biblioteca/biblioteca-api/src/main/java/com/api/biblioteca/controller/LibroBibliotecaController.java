package com.api.biblioteca.controller;

import com.api.biblioteca.model.Libro;
import com.api.biblioteca.service.LibroBibliotecaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("libros")
@CrossOrigin("*")
@RequiredArgsConstructor
public class LibroBibliotecaController {

    private final LibroBibliotecaService libroBibliotecaService;

    @GetMapping("/biblioteca/{id}")
    public ResponseEntity<List<Libro>> obtenerLibrosPorBiblioteca(@PathVariable Integer id) {

        List<Libro> libros = libroBibliotecaService.listarLibrosPorBiblioteca(id);
        return ResponseEntity.ok(libros);
    }
}
