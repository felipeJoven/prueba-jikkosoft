package com.api.blog.repository;

import com.api.blog.model.RecuperarClave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecuperarClaveRepository extends JpaRepository<RecuperarClave, Integer> {
}
