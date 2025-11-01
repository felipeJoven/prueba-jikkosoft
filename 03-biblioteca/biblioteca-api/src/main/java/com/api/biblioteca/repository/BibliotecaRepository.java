package com.api.biblioteca.repository;

import com.api.biblioteca.model.Biblioteca;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaRepository extends BaseRepository<Biblioteca, Integer> {

    boolean existsByDireccion(String direccion);
}
