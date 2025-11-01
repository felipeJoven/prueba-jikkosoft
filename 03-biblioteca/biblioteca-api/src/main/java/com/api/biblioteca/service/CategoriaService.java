package com.api.biblioteca.service;

import com.api.biblioteca.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> listarCategorias();
    Optional<Categoria> listarCategoriaPorId(Integer id);
    Categoria guardarCategoria(Categoria categoria);
    Categoria actualizarCategoria(Integer id, Categoria categoriaNueva);
    void eliminarCategoria(Integer id);

}
