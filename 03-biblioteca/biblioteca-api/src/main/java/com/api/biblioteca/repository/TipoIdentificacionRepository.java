package com.api.biblioteca.repository;

import com.api.biblioteca.model.TipoIdentificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Integer> {
}
