package com.api.biblioteca.service.impl;

import com.api.biblioteca.exception.ConflictException;
import com.api.biblioteca.exception.NotFoundException;
import com.api.biblioteca.model.Categoria;
import com.api.biblioteca.repository.CategoriaRepository;
import com.api.biblioteca.service.CategoriaService;
import com.api.biblioteca.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarCategorias() {

        List<Categoria> categorias = categoriaRepository.findAll();

        if (categorias.isEmpty()) {
            throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "categorias");
        }

        return categorias;
    }

    @Override
    public Optional<Categoria> listarCategoriaPorId(Integer id) {

        Optional<Categoria> categoriaId = categoriaRepository.findById(id);

        if (categoriaId.isEmpty()){
            throw new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id);
        }

        return categoriaId;
    }

    @Override
    @Transactional
    public Categoria guardarCategoria(Categoria categoria) {

        boolean existeCategoria = categoriaRepository.existsByNombre(categoria.getNombre());

        if (existeCategoria) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "la categoria");
        }

        categoria.setFechaCreacion(LocalDate.now());
        categoria.setFechaActualizacion(LocalDate.now());

        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public Categoria actualizarCategoria(Integer id, Categoria categoriaNueva) {

        Categoria categoriaActual = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        boolean existeCategoria = categoriaRepository.existsByNombre(categoriaNueva.getNombre());
        boolean cambioNombre = !categoriaNueva.getNombre().equals(categoriaActual.getNombre());

        if (cambioNombre && existeCategoria) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "la categoria");
        }

        categoriaActual.setNombre(categoriaNueva.getNombre());
        categoriaActual.setFechaActualizacion(LocalDate.now());

        return categoriaRepository.save(categoriaActual);
    }

    @Override
    public void eliminarCategoria(Integer id) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        categoriaRepository.delete(categoria);
    }
}
