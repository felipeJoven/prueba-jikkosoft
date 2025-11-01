package com.api.biblioteca.service.impl;

import com.api.biblioteca.model.Libro;
import com.api.biblioteca.repository.LibroCategoriaRepository;
import com.api.biblioteca.service.LibroCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroCategoriaServiceImpl implements LibroCategoriaService {

    private final LibroCategoriaRepository libroCategoriaRepository;

    @Override
    public List<Libro> listarLibrosPorCategoria(Integer categoriaId) {
        return libroCategoriaRepository.findLibrosByCategoriaId(categoriaId);
    }
}
