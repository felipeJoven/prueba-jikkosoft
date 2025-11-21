package com.api.biblioteca.service.impl;

import com.api.biblioteca.exception.ConflictException;
import com.api.biblioteca.exception.NotFoundException;
import com.api.biblioteca.model.Biblioteca;
import com.api.biblioteca.repository.BibliotecaRepository;
import com.api.biblioteca.service.BibliotecaService;
import com.api.biblioteca.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BibliotecaServiceImpl implements BibliotecaService {

    private final BibliotecaRepository bibliotecaRepository;

    @Override
    public List<Biblioteca> listarBibliotecas() {

        List<Biblioteca> bibliotecas = bibliotecaRepository.findAll();

        if (bibliotecas.isEmpty()) {
            throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "bibliotecas");
        }

        return bibliotecas;
    }

    @Override
    public Optional<Biblioteca> listarBibliotecaPorId(Integer id) {

        Optional<Biblioteca> bibliotecaId = bibliotecaRepository.findById(id);

        if (bibliotecaId.isEmpty()){
            throw new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id);
        }

        return bibliotecaId;
    }

    @Override
    @Transactional
    public Biblioteca guardarBiblioteca(Biblioteca biblioteca) {

        boolean exiteBiblioteca = bibliotecaRepository.existsByDireccion(biblioteca.getDireccion());

        if (exiteBiblioteca) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "la biblioteca");
        }

        biblioteca.setFechaCreacion(LocalDate.now());
        biblioteca.setFechaActualizacion(LocalDate.now());

        return bibliotecaRepository.save(biblioteca);

    }

    @Override
    @Transactional
    public Biblioteca actualizarBiblioteca(Integer id, Biblioteca bibliotecaNueva) {

        Biblioteca bibliotecaActual = bibliotecaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        boolean exiteBiblioteca = bibliotecaRepository.existsByDireccion(bibliotecaNueva.getDireccion());
        boolean cambioDireccion = !bibliotecaNueva.getDireccion().equalsIgnoreCase(bibliotecaActual.getDireccion());

        if (cambioDireccion && exiteBiblioteca) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "la biblioteca");
        }

        bibliotecaActual.setDireccion(bibliotecaNueva.getDireccion());
        bibliotecaActual.setBarrio(bibliotecaNueva.getBarrio());
        bibliotecaActual.setTelefono(bibliotecaNueva.getTelefono());
        bibliotecaActual.setFechaActualizacion(LocalDate.now());

        return bibliotecaRepository.save(bibliotecaActual);
    }

    @Override
    public void eliminarBiblioteca(Integer id) {

        Biblioteca biblioteca = bibliotecaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        bibliotecaRepository.delete(biblioteca);
    }
}
