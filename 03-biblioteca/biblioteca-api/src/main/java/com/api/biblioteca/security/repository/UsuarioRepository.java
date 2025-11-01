package com.api.biblioteca.security.repository;

import com.api.biblioteca.repository.BaseRepository;
import com.api.biblioteca.security.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Integer> {

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
}
