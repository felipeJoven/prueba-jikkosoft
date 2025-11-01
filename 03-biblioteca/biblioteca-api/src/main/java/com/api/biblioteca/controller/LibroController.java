package com.api.biblioteca.controller;

import com.api.biblioteca.dto.LibroRequestDto;
import com.api.biblioteca.dto.LibroResponseDto;
import com.api.biblioteca.dto.PrestamoDto;
import com.api.biblioteca.model.Libro;
import com.api.biblioteca.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("libro")
@CrossOrigin("*")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;

    @GetMapping("")
    public ResponseEntity<List<LibroResponseDto>> obtenerLibros(@RequestParam(required = false) String filtro) {

        List<LibroResponseDto> libros = libroService.listarLibros(filtro);
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<LibroResponseDto>> obtenerLibroPorId(@PathVariable Integer id) {

        Optional<LibroResponseDto> libro = libroService.listarLibroPorId(id);
        return ResponseEntity.ok(libro);
    }

    @PostMapping("")
    public ResponseEntity<LibroResponseDto> crearLibro(@RequestBody LibroRequestDto requestDto) {

        LibroResponseDto libroNuevo = libroService.guardarLibro(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroResponseDto> editarLibro(@PathVariable Integer id, @RequestBody LibroRequestDto requestDto) {

        LibroResponseDto libroActualizado = libroService.actualizarLibro(id, requestDto);
        return ResponseEntity.ok(libroActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarLibro(@PathVariable Integer id) {

        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}
