package com.api.biblioteca.repository;

import com.api.biblioteca.model.Miembro;
import org.springframework.stereotype.Repository;

@Repository
public interface MiembroRepository extends BaseRepository<Miembro, Integer> {

    boolean existsByNumeroIdentificacion(String numeroIdentificacion);
}
