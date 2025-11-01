package com.api.biblioteca.repository;

import com.api.biblioteca.model.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends BaseRepository<Categoria, Integer> {

    boolean existsByNombre(String nombre);
}
