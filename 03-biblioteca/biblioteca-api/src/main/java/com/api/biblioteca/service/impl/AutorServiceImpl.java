package com.api.biblioteca.service.impl;

import com.api.biblioteca.exception.ConflictException;
import com.api.biblioteca.exception.NotFoundException;
import com.api.biblioteca.model.Autor;
import com.api.biblioteca.repository.AutorRepository;
import com.api.biblioteca.service.AutorService;
import com.api.biblioteca.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;

    @Override
    public List<Autor> listarAutores() {

        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "autores");
        }

        return autores;
    }

    @Override
    public Optional<Autor> listarAutorPorId(Integer id) {

        Optional<Autor> autorId = autorRepository.findById(id);

        if (autorId.isEmpty()) {
            throw new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id);
        }

        return autorId;
    }

    @Override
    @Transactional
    public Autor guardarAutor(Autor autor) {

        boolean existeAutor = autorRepository.existsByNombre(autor.getNombre());

        if (existeAutor) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el autor");
        }

        autor.setFechaCreacion(LocalDate.now());
        autor.setFechaActualizacion(LocalDate.now());

        return autorRepository.save(autor);

    }

    @Override
    @Transactional
    public Autor actualizarAutor(Integer id, Autor autorActual) {

        Autor autorNuevo = autorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        boolean existeAutor = autorRepository.existsByNombre(autorActual.getNombre());
        boolean cambioNombre = !autorNuevo.getNombre().equalsIgnoreCase(autorActual.getNombre());

        if (cambioNombre && existeAutor) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el autor");
        }

        autorActual.setNombre(autorNuevo.getNombre());
        autorActual.setFechaActualizacion(LocalDate.now());

        return autorRepository.save(autorActual);
    }

    @Override
    public void eliminarAutor(Integer id) {

        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        autorRepository.delete(autor);
    }
}
