package com.api.biblioteca.service;

import com.api.biblioteca.dto.MiembroRequestDto;
import com.api.biblioteca.dto.MiembroResponseDto;

import java.util.List;
import java.util.Optional;

public interface MiembroService {
    
    List<MiembroResponseDto> listarMiembros();
    Optional<MiembroResponseDto> listarMiembroPorId(Integer id);
    MiembroResponseDto guardarMiembro(MiembroRequestDto miembroNuevo);
    MiembroResponseDto actualizarMiembro(Integer id, MiembroRequestDto miembroNuevo);
    void eliminarMiembro(Integer id);
}
