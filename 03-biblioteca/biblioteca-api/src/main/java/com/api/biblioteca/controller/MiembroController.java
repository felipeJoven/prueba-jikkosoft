package com.api.biblioteca.controller;

import com.api.biblioteca.dto.MiembroRequestDto;
import com.api.biblioteca.dto.MiembroResponseDto;
import com.api.biblioteca.service.MiembroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("miembro")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MiembroController {

    private final MiembroService miembroService;

    @GetMapping("")
    public ResponseEntity<List<MiembroResponseDto>> obtenerMiembros() {

        List<MiembroResponseDto> miembros = miembroService.listarMiembros();

        return ResponseEntity.ok(miembros);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<MiembroResponseDto>> obtenerMiembroPorId(@PathVariable Integer id) {

        Optional<MiembroResponseDto> miembro = miembroService.listarMiembroPorId(id);
        return ResponseEntity.ok(miembro);
    }

    @PostMapping("")
    public ResponseEntity<MiembroResponseDto> crearMiembro(@RequestBody MiembroRequestDto requestDto) {

        MiembroResponseDto miembroNueva = miembroService.guardarMiembro(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(miembroNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MiembroResponseDto> editarMiembro(@PathVariable Integer id, @RequestBody MiembroRequestDto requestDto) {

        MiembroResponseDto miembroActualizado = miembroService.actualizarMiembro(id, requestDto);
        return ResponseEntity.ok(miembroActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarMiembro(@PathVariable Integer id) {

        miembroService.eliminarMiembro(id);
        return ResponseEntity.noContent().build();
    }
}
