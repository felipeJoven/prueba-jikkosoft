package com.api.biblioteca.service.impl;

import com.api.biblioteca.dto.PrestamoDto;
import com.api.biblioteca.dto.PrestamoRequestDto;
import com.api.biblioteca.dto.PrestamoResponseDto;
import com.api.biblioteca.exception.ConflictException;
import com.api.biblioteca.exception.NotFoundException;
import com.api.biblioteca.model.Libro;
import com.api.biblioteca.model.Miembro;
import com.api.biblioteca.model.Prestamo;
import com.api.biblioteca.repository.LibroRepository;
import com.api.biblioteca.repository.MiembroRepository;
import com.api.biblioteca.repository.PrestamoRepository;
import com.api.biblioteca.service.PrestamoService;
import com.api.biblioteca.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final MiembroRepository miembroRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PrestamoDto> listarPrestamos() {

        List<Prestamo> prestamos = prestamoRepository.findAll();

        if (prestamos.isEmpty()) {
            throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "prestamos");
        }

        return prestamos.stream()
                .map(prestamo -> modelMapper.map(prestamo, PrestamoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PrestamoDto> listarPrestamoPorId(Integer id) {

        Optional<Prestamo> prestamoId = prestamoRepository.findById(id);

        if (prestamoId.isEmpty()){
            throw new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id);
        }

        return prestamoId.map(prestamo -> modelMapper.map(prestamo, PrestamoDto.class));
    }

    @Override
    @Transactional
    public PrestamoResponseDto guardarPrestamo(PrestamoRequestDto prestamoNuevo) {

        Libro libro = libroRepository.findById(prestamoNuevo.getLibroId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "el libro"));

        Miembro miembro = miembroRepository.findById(prestamoNuevo.getMiembroId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "el miembro"));

        boolean existePrestamo = prestamoRepository.existsByLibroAndDevuelto(libro, false);

        if (existePrestamo) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el prestamo");
        }

        Prestamo prestamo = modelMapper.map(prestamoNuevo, Prestamo.class);

        prestamo.setId(null);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(prestamoNuevo.getFechaDevolucion());
        prestamo.setLibro(libro);
        prestamo.setMiembro(miembro);
        prestamo.setFechaCreacion(LocalDate.now());
        prestamo.setFechaActualizacion(LocalDate.now());

        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);

        PrestamoResponseDto responseDto = modelMapper.map(prestamoGuardado, PrestamoResponseDto.class);
        responseDto.setLibroId(libro.getId());
        responseDto.setLibro(libro.getTitulo());
        responseDto.setMiembroId(miembro.getId());
        responseDto.setMiembro(miembro.getNombre());

        return responseDto;
    }

    @Override
    @Transactional
    public PrestamoResponseDto actualizarPrestamo(Integer id, PrestamoRequestDto prestamoNuevo) {

        Prestamo prestamoActual = prestamoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        Libro libro = libroRepository.findById(prestamoNuevo.getLibroId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "el libro"));

        Miembro miembro = miembroRepository.findById(prestamoNuevo.getMiembroId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "el miembro"));

        if (!prestamoActual.getLibro().getId().equals(libro.getId())) {
            boolean libroPrestado = prestamoRepository.existsByLibroAndDevuelto(libro, false);
            if (libroPrestado) {
                throw new ConflictException("El libro nuevo ya está prestado y no ha sido devuelto.");
            }
        }

        prestamoActual.setFechaDevolucion(prestamoNuevo.getFechaDevolucion());
        prestamoActual.setLibro(libro);
        prestamoActual.setMiembro(miembro);
        prestamoActual.setDevuelto(prestamoNuevo.isDevuelto());
        prestamoActual.setFechaActualizacion(LocalDate.now());

        Prestamo prestamoActualizado = prestamoRepository.save(prestamoActual);

        PrestamoResponseDto responseDto = modelMapper.map(prestamoActualizado, PrestamoResponseDto.class);
        responseDto.setLibroId(libro.getId());
        responseDto.setLibro(libro.getTitulo());
        responseDto.setMiembroId(miembro.getId());
        responseDto.setMiembro(miembro.getNombre());

        return responseDto;
    }

    @Override
    @Transactional
    public PrestamoResponseDto devolverPrestamo(Integer id) {

        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró el préstamo con id " + id));

        prestamo.setDevuelto(true);
        prestamo.setFechaActualizacion(LocalDate.now());

        Prestamo guardado = prestamoRepository.save(prestamo);

        return modelMapper.map(guardado, PrestamoResponseDto.class);
    }

    @Override
    public void eliminarPrestamo(Integer id) {

        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        prestamoRepository.delete(prestamo);
    }
}
