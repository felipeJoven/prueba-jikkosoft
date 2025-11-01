package com.api.biblioteca.service;

import com.api.biblioteca.model.Miembro;

import java.util.List;
import java.util.Optional;

public interface MiembroService {
    
    List<Miembro> listarMiembros();
    Optional<Miembro> listarMiembroPorId(Integer id);
    Miembro guardarMiembro(Miembro miembro);
    Miembro actualizarMiembro(Integer id, Miembro miembroNuevo);
    void eliminarMiembro(Integer id);
}
