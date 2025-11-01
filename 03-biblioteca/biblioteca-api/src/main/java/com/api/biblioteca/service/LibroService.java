package com.api.biblioteca.service;


import com.api.biblioteca.dto.LibroRequestDto;
import com.api.biblioteca.dto.LibroResponseDto;
import com.api.biblioteca.model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroService {

    List<LibroResponseDto> listarLibros(String filtro);
    Optional<LibroResponseDto> listarLibroPorId(Integer id);
    LibroResponseDto guardarLibro(LibroRequestDto libroNuevo);
    LibroResponseDto actualizarLibro(Integer id, LibroRequestDto libroNuevo);
    void eliminarLibro(Integer id);
}
