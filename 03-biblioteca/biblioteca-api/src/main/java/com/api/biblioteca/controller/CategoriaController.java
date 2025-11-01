package com.api.biblioteca.controller;

import com.api.biblioteca.model.Categoria;
import com.api.biblioteca.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("categoria")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("")
    public ResponseEntity<?> obtenerCategorias() {

        List<Categoria> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> obtenerCategoriaPorId(@PathVariable Integer id) {

        Optional<Categoria> categoria = categoriaService.listarCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping("")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {

        Categoria categoriaNueva = categoriaService.guardarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {

        Categoria categoriaActualizada = categoriaService.actualizarCategoria(id, categoria);
        return ResponseEntity.ok(categoriaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarCategoria(@PathVariable Integer id) {

        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
