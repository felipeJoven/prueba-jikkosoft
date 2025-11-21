package com.api.biblioteca.controller;

import com.api.biblioteca.dto.PrestamoRequestDto;
import com.api.biblioteca.dto.PrestamoResponseDto;
import com.api.biblioteca.dto.PrestamoDto;
import com.api.biblioteca.service.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("prestamo")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoService prestamoService;

    @GetMapping("")
    public ResponseEntity<List<PrestamoDto>> obtenerPrestamos() {

        List<PrestamoDto> prestamos = prestamoService.listarPrestamos();

        return ResponseEntity.ok(prestamos);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<PrestamoDto>> obtenerPrestamoPorId(@PathVariable Integer id) {

        Optional<PrestamoDto> prestamo = prestamoService.listarPrestamoPorId(id);

        return ResponseEntity.ok(prestamo);
    }

    @PostMapping("")
    public ResponseEntity<PrestamoResponseDto> crearPrestamo(@RequestBody PrestamoRequestDto requestDto) {

        PrestamoResponseDto prestamoNuevo = prestamoService.guardarPrestamo(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestamoResponseDto> editarPrestamo(@PathVariable Integer id, @RequestBody PrestamoRequestDto requestDto) {

        PrestamoResponseDto prestamoActualizado = prestamoService.actualizarPrestamo(id, requestDto);
        return ResponseEntity.ok(prestamoActualizado);
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<PrestamoResponseDto> devolverPrestamo(@PathVariable Integer id) {

        PrestamoResponseDto response = prestamoService.devolverPrestamo(id);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarPrestamo(@PathVariable Integer id) {

        prestamoService.eliminarPrestamo(id);
        return ResponseEntity.noContent().build();
    }
}
