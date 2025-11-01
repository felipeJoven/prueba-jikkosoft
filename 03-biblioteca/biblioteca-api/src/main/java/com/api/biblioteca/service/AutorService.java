package com.api.biblioteca.service;

import com.api.biblioteca.model.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorService {

    List<Autor> listarAutores();
    Optional<Autor> listarAutorPorId(Integer id);
    Autor guardarAutor(Autor autor);
    Autor actualizarAutor(Integer id, Autor autorNuevo);
    void eliminarAutor(Integer id);
}
