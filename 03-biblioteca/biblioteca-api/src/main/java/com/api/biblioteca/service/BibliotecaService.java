package com.api.biblioteca.service;

import com.api.biblioteca.model.Biblioteca;

import java.util.List;
import java.util.Optional;

public interface BibliotecaService {

    List<Biblioteca> listarBibliotecas();
    Optional<Biblioteca> listarBibliotecaPorId(Integer id);
    Biblioteca guardarBiblioteca(Biblioteca biblioteca);
    Biblioteca actualizarBiblioteca(Integer id, Biblioteca bibliotecaNueva);
    void eliminarBiblioteca(Integer id);
}
