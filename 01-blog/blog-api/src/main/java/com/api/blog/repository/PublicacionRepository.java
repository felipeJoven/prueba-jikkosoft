package com.api.blog.repository;

import com.api.blog.model.Publicacion;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository extends BaseRepository<Publicacion, Integer> {
}
