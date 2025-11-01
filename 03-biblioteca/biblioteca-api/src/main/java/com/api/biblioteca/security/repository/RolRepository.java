package com.api.biblioteca.security.repository;

import com.api.biblioteca.repository.BaseRepository;
import com.api.biblioteca.security.model.Rol;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends BaseRepository<Rol, Integer> {

    Optional<Rol> findByRol(String rol);
}
