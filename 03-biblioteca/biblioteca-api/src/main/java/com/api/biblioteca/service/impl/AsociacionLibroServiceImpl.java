package com.api.biblioteca.service.impl;

import com.api.biblioteca.model.*;
import com.api.biblioteca.repository.*;
import com.api.biblioteca.service.AsociacionLibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AsociacionLibroServiceImpl implements AsociacionLibroService {

    private final LibroRepository libroRepository;
    private final CategoriaRepository categoriaRepository;
    private final BibliotecaRepository bibliotecaRepository;
    private final LibroCategoriaRepository libroCategoriaRepository;
    private final LibroBibliotecaRepository libroBibliotecaRepository;

    @Override
    @Transactional
    public void asociarLibroConCategoriaYBiblioteca(Integer libroId, Integer categoriaId, Integer bibliotecaId) {

        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado"));
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        Biblioteca biblioteca = bibliotecaRepository.findById(bibliotecaId)
                .orElseThrow(() -> new IllegalArgumentException("Biblioteca no encontrada"));

        LibroCategoria libroCategoria = new LibroCategoria();
        libroCategoria.setLibro(libro);
        libroCategoria.setCategoria(categoria);
        libroCategoriaRepository.save(libroCategoria);

        LibroBiblioteca libroBiblioteca = new LibroBiblioteca();
        libroBiblioteca.setLibro(libro);
        libroBiblioteca.setBiblioteca(biblioteca);
        libroBibliotecaRepository.save(libroBiblioteca);
    }
}
