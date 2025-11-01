package com.api.biblioteca.controller;

import com.api.biblioteca.model.Biblioteca;
import com.api.biblioteca.service.BibliotecaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("biblioteca")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    @GetMapping("")
    public ResponseEntity<?> obtenerBibliotecas() {

        List<Biblioteca> bibliotecas = bibliotecaService.listarBibliotecas();
        return ResponseEntity.ok(bibliotecas);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> obtenerBibliotecaPorId(@PathVariable Integer id) {

        Optional<Biblioteca> biblioteca = bibliotecaService.listarBibliotecaPorId(id);
        return ResponseEntity.ok(biblioteca);
    }

    @PostMapping("")
    public ResponseEntity<Biblioteca> crearBiblioteca(@RequestBody Biblioteca biblioteca) {

        Biblioteca bibliotecaNueva = bibliotecaService.guardarBiblioteca(biblioteca);
        return ResponseEntity.status(HttpStatus.CREATED).body(bibliotecaNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> editarBiblioteca(@PathVariable Integer id, @RequestBody Biblioteca biblioteca) {

        Biblioteca bibliotecaActualizada = bibliotecaService.actualizarBiblioteca(id, biblioteca);
        return ResponseEntity.ok(bibliotecaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarBiblioteca(@PathVariable Integer id) {

        bibliotecaService.eliminarBiblioteca(id);
        return ResponseEntity.noContent().build();
    }
}
