package com.api.biblioteca.repository;

import com.api.biblioteca.model.Libro;
import com.api.biblioteca.model.LibroBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroBibliotecaRepository extends JpaRepository<LibroBiblioteca, Integer> {

    @Query("SELECT lb.libro FROM LibroBiblioteca lb WHERE lb.biblioteca.id = :bibliotecaId")
    List<Libro> findLibrosByBibliotecaId(@Param("bibliotecaId") Integer bibliotecaId);
}
