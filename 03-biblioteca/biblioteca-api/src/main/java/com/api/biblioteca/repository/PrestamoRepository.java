package com.api.biblioteca.repository;

import com.api.biblioteca.model.Libro;
import com.api.biblioteca.model.Prestamo;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends BaseRepository<Prestamo, Integer> {

    boolean existsByLibroAndDevuelto(Libro libro, boolean devuelto);
}
