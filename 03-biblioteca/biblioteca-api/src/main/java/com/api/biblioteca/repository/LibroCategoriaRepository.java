package com.api.biblioteca.repository;

import com.api.biblioteca.model.Libro;
import com.api.biblioteca.model.LibroCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroCategoriaRepository extends JpaRepository<LibroCategoria, Integer> {

    @Query("SELECT lc.libro FROM LibroCategoria lc WHERE lc.categoria.id = :categoriaId")
    List<Libro> findLibrosByCategoriaId(@Param("categoriaId") Integer categoriaId);
}
