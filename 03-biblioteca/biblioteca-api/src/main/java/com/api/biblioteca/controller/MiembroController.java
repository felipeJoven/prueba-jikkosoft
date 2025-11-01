package com.api.biblioteca.controller;

import com.api.biblioteca.model.Miembro;
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
    public ResponseEntity<?> obtenerMiembros() {

        List<Miembro> miembros = miembroService.listarMiembros();

        return ResponseEntity.ok(miembros);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> obtenerMiembroPorId(@PathVariable Integer id) {

        Optional<Miembro> miembro = miembroService.listarMiembroPorId(id);
        return ResponseEntity.ok(miembro);
    }

    @PostMapping("")
    public ResponseEntity<Miembro> crearMiembro(@RequestBody Miembro miembro) {

        Miembro miembroNueva = miembroService.guardarMiembro(miembro);
        return ResponseEntity.status(HttpStatus.CREATED).body(miembroNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Miembro> editarMiembro(@PathVariable Integer id, @RequestBody Miembro miembro) {

        Miembro miembroActualizado = miembroService.actualizarMiembro(id, miembro);
        return ResponseEntity.ok(miembroActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarMiembro(@PathVariable Integer id) {

        miembroService.eliminarMiembro(id);
        return ResponseEntity.noContent().build();
    }
}
