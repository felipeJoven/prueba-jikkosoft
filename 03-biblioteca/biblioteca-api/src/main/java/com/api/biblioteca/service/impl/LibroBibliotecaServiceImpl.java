package com.api.biblioteca.service.impl;

import com.api.biblioteca.model.Libro;
import com.api.biblioteca.repository.LibroBibliotecaRepository;
import com.api.biblioteca.service.LibroBibliotecaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroBibliotecaServiceImpl implements LibroBibliotecaService {

    private final LibroBibliotecaRepository repository;

    public List<Libro> listarLibrosPorBiblioteca(Integer bibliotecaId) {
        return repository.findLibrosByBibliotecaId(bibliotecaId);
    }

}
