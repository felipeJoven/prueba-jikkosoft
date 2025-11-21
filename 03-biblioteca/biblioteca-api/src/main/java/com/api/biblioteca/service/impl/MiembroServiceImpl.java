package com.api.biblioteca.service.impl;

import com.api.biblioteca.dto.MiembroRequestDto;
import com.api.biblioteca.dto.MiembroResponseDto;
import com.api.biblioteca.exception.ConflictException;
import com.api.biblioteca.exception.NotFoundException;
import com.api.biblioteca.model.Biblioteca;
import com.api.biblioteca.model.Miembro;
import com.api.biblioteca.model.TipoIdentificacion;
import com.api.biblioteca.repository.BibliotecaRepository;
import com.api.biblioteca.repository.MiembroRepository;
import com.api.biblioteca.repository.TipoIdentificacionRepository;
import com.api.biblioteca.service.MiembroService;
import com.api.biblioteca.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MiembroServiceImpl implements MiembroService {
    
    private final MiembroRepository miembroRepository;
    private final TipoIdentificacionRepository tipoIdentificacionRepository;
    private final BibliotecaRepository bibliotecaRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<MiembroResponseDto> listarMiembros() {
        
        List<Miembro> miembros = miembroRepository.findAll();

        if (miembros.isEmpty()) {
            throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "miembros");
        }

        return miembros.stream()
                .map(miembro -> modelMapper.map(miembro, MiembroResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MiembroResponseDto> listarMiembroPorId(Integer id) {

        Optional<Miembro> miembroId = miembroRepository.findById(id);

        if (miembroId.isEmpty()){
            throw new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id);
        }

        return miembroId.map(miembro -> modelMapper.map(miembro, MiembroResponseDto.class));
    }

    @Override
    public MiembroResponseDto guardarMiembro(MiembroRequestDto miembroNuevo) {

        TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(miembroNuevo.getTipoIdentificacionId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "el tipo de identificación"));

        Biblioteca biblioteca = bibliotecaRepository.findById(miembroNuevo.getBibliotecaId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "la biblioteca"));

        boolean existeMiembro = miembroRepository.existsByNumeroIdentificacion(miembroNuevo.getNumeroIdentificacion());

        if (existeMiembro) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el miembro");
        }

        Miembro miembro = modelMapper.map(miembroNuevo, Miembro.class);

        miembro.setId(null);
        miembro.setBiblioteca(biblioteca);
        miembro.setNombre(miembroNuevo.getNombre());
        miembro.setApellido(miembroNuevo.getApellido());
        miembro.setTipoIdentificacion(tipoIdentificacion);
        miembro.setNumeroIdentificacion(miembroNuevo.getNumeroIdentificacion());
        miembro.setTelefono(miembroNuevo.getTelefono());
        miembro.setActivo(true);
        miembro.setFechaCreacion(LocalDate.now());
        miembro.setFechaActualizacion(LocalDate.now());

        Miembro miembroGuardado = miembroRepository.save(miembro);

        MiembroResponseDto responseDto = modelMapper.map(miembroGuardado, MiembroResponseDto.class);
        responseDto.setBibliotecaId(biblioteca.getId());
        responseDto.setNombreBiblioteca(biblioteca.getNombre());
        responseDto.setTipoIdentificacionId(tipoIdentificacion.getId());
        responseDto.setTipoIdentificacion(tipoIdentificacion.getTipo());

        return responseDto;
    }

    @Override
    public MiembroResponseDto actualizarMiembro(Integer id, MiembroRequestDto miembroNuevo) {

        Miembro miembroActual = miembroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(miembroNuevo.getTipoIdentificacionId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "el tipo de identificación"));

        Biblioteca biblioteca = bibliotecaRepository.findById(miembroNuevo.getBibliotecaId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "la biblioteca"));

        boolean existeMiembro = miembroRepository.existsByNumeroIdentificacion(miembroNuevo.getNumeroIdentificacion());
        boolean cambioNumero = !miembroNuevo.getNombre().equals(miembroActual.getNombre());

        if (cambioNumero && existeMiembro) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el miembro");
        }

        miembroActual.setBiblioteca(biblioteca);
        miembroActual.setNombre(miembroNuevo.getNombre());
        miembroActual.setApellido(miembroNuevo.getApellido());
        miembroActual.setTipoIdentificacion(tipoIdentificacion);
        miembroActual.setNumeroIdentificacion(miembroNuevo.getNumeroIdentificacion());
        miembroActual.setTelefono(miembroNuevo.getTelefono());
        miembroActual.setActivo(miembroNuevo.isActivo());
        miembroActual.setFechaActualizacion(LocalDate.now());

        Miembro miembroGuardado = miembroRepository.save(miembroActual);

        MiembroResponseDto responseDto = modelMapper.map(miembroGuardado, MiembroResponseDto.class);
        responseDto.setBibliotecaId(biblioteca.getId());
        responseDto.setNombreBiblioteca(biblioteca.getNombre());
        responseDto.setTipoIdentificacionId(tipoIdentificacion.getId());
        responseDto.setTipoIdentificacion(tipoIdentificacion.getTipo());

        return responseDto;
    }

    @Override
    public void eliminarMiembro(Integer id) {

        Miembro miembro = miembroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        miembroRepository.delete(miembro);
    }
}
