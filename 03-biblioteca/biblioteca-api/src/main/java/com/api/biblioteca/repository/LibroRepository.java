package com.api.biblioteca.repository;

import com.api.biblioteca.model.Libro;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends BaseRepository<Libro, Integer> {

    boolean existsByIsbn(String isbn);
}
