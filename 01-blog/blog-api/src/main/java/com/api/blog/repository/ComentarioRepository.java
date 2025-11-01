package com.api.blog.repository;

import com.api.blog.model.Comentario;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends BaseRepository<Comentario, Integer> {
}
