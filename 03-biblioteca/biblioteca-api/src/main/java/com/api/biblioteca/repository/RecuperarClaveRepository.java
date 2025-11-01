package com.api.biblioteca.repository;

import com.api.biblioteca.model.RecuperarClave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecuperarClaveRepository extends JpaRepository<RecuperarClave, Integer> {
}
