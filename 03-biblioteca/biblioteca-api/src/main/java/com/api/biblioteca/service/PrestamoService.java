package com.api.biblioteca.service;

import com.api.biblioteca.dto.PrestamoDto;
import com.api.biblioteca.dto.PrestamoRequestDto;
import com.api.biblioteca.dto.PrestamoResponseDto;

import java.util.List;
import java.util.Optional;

public interface PrestamoService {

    List<PrestamoDto> listarPrestamos();
    Optional<PrestamoDto> listarPrestamoPorId(Integer id);
    PrestamoResponseDto guardarPrestamo(PrestamoRequestDto prestamoNuevo);
    PrestamoResponseDto devolverPrestamo(Integer id);
    PrestamoResponseDto actualizarPrestamo(Integer id, PrestamoRequestDto prestamoNuevo);
    void eliminarPrestamo(Integer id);
}
