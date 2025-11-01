package com.api.biblioteca.service;

public interface AsociacionLibroService {

    void asociarLibroConCategoriaYBiblioteca(Integer libroId, Integer categoriaId, Integer bibliotecaId);
}
