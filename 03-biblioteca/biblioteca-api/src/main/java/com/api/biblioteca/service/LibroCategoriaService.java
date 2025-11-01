package com.api.biblioteca.service;

import com.api.biblioteca.model.Libro;

import java.util.List;

public interface LibroCategoriaService {

    List<Libro> listarLibrosPorCategoria(Integer id);
}
