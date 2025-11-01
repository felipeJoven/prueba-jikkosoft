package com.api.biblioteca.controller;

import com.api.biblioteca.model.Autor;
import com.api.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("autor")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @GetMapping("")
    public ResponseEntity<?> obtenerAutors() {

        List<Autor> autores = autorService.listarAutores();
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> obtenerAutorPorId(@PathVariable Integer id) {

        Optional<Autor> autor = autorService.listarAutorPorId(id);
        return ResponseEntity.ok(autor);
    }

    @PostMapping("")
    public ResponseEntity<?> crearAutor(@RequestBody Autor autor) {

        Autor autorNuevo = autorService.guardarAutor(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarAutor(@PathVariable Integer id, @RequestBody Autor autor) {

        Autor autorActualizado = autorService.actualizarAutor(id, autor);
        return ResponseEntity.ok(autorActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarAutor(@PathVariable Integer id) {

        autorService.eliminarAutor(id);
        return ResponseEntity.noContent().build();
    }
}
