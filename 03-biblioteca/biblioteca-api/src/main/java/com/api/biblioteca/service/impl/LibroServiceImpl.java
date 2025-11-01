package com.api.biblioteca.service.impl;

import com.api.biblioteca.dto.LibroRequestDto;
import com.api.biblioteca.dto.LibroResponseDto;
import com.api.biblioteca.exception.ConflictException;
import com.api.biblioteca.exception.NotFoundException;
import com.api.biblioteca.model.Autor;
import com.api.biblioteca.model.Libro;
import com.api.biblioteca.repository.AutorRepository;
import com.api.biblioteca.repository.LibroRepository;
import com.api.biblioteca.service.LibroService;
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
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<LibroResponseDto> listarLibros(String filtro) {

        List<Libro> libros = libroRepository.findAll();

        if (libros.isEmpty()) {
            throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "libros");
        }

        return libros.stream()
                .map(libro -> modelMapper.map(libro, LibroResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LibroResponseDto> listarLibroPorId(Integer id) {

        Optional<Libro> libroId = libroRepository.findById(id);

        if (libroId.isEmpty()){
            throw new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id);
        }

        return libroId.map(libro -> modelMapper.map(libro, LibroResponseDto.class));
    }

    @Override
    @Transactional
    public LibroResponseDto guardarLibro(LibroRequestDto libroNuevo) {

        boolean existeLibro = libroRepository.existsByIsbn(libroNuevo.getIsbn());

        if (existeLibro) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el libro");
        }

        Autor autorNuevo = autorRepository.findById(libroNuevo.getAutorId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "el autor"));

        Libro libro = modelMapper.map(libroNuevo, Libro.class);

        libro.setId(null);
        libro.setAutor(autorNuevo);
        libro.setFechaCreacion(LocalDate.now());
        libro.setFechaActualizacion(LocalDate.now());

        Libro libroGuardado = libroRepository.save(libro);

        LibroResponseDto responseDto = modelMapper.map(libroGuardado, LibroResponseDto.class);
        responseDto.setAutorId(autorNuevo.getId());
        responseDto.setNombreAutor(autorNuevo.getNombre());

        return responseDto;
    }


    @Override
    @Transactional
    public LibroResponseDto actualizarLibro(Integer id, LibroRequestDto libroNuevo) {

        Libro libroActual = libroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        Autor autor = autorRepository.findById(libroNuevo.getAutorId())
                        .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "el autor"));

        boolean existeLibro = libroRepository.existsByIsbn(libroNuevo.getIsbn());
        boolean cambioIsbn = !libroActual.getIsbn().equalsIgnoreCase(libroNuevo.getIsbn());

        if (cambioIsbn && existeLibro) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el libro");
        }

        libroActual.setTitulo(libroNuevo.getTitulo());
        libroActual.setIsbn(libroNuevo.getIsbn());
        libroActual.setAutor(autor);
        libroActual.setFechaActualizacion(LocalDate.now());

        Libro libroActualizado = libroRepository.save(libroActual);

        LibroResponseDto responseDto = modelMapper.map(libroActualizado, LibroResponseDto.class);
        responseDto.setAutorId(autor.getId());
        responseDto.setNombreAutor(autor.getNombre());

        return responseDto;
    }

    @Override
    public void eliminarLibro(Integer id) {

        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        libroRepository.delete(libro);
    }
}
