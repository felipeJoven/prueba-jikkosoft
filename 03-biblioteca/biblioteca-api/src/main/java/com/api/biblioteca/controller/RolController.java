package com.api.biblioteca.controller;

import com.api.biblioteca.security.model.Rol;
import com.api.biblioteca.security.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rol")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping("")
    public ResponseEntity<List<Rol>> obtenerRoles() {

        List<Rol> roles = rolService.listarRoles();
        return ResponseEntity.ok(roles);
    }
}
