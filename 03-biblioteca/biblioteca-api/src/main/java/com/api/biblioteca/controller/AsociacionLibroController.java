package com.api.biblioteca.controller;

import com.api.biblioteca.service.AsociacionLibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("libro")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AsociacionLibroController {

    private final AsociacionLibroService AsociacionLibroService;

    @PostMapping("/asociaciones")
    public ResponseEntity<String> asociarLibro(@RequestParam Integer libroId,
                                               @RequestParam Integer categoriaId,
                                               @RequestParam Integer bibliotecaId) {

        AsociacionLibroService.asociarLibroConCategoriaYBiblioteca(libroId, categoriaId, bibliotecaId);
        return ResponseEntity.ok("Se ha asociado el libro correctamente");
    }
}