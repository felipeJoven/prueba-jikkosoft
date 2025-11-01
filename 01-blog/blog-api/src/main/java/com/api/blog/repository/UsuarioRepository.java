package com.api.blog.repository;

import com.api.blog.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Integer> {
}
