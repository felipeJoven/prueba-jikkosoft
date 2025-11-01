package com.api.biblioteca.service.impl;

import com.api.biblioteca.exception.ConflictException;
import com.api.biblioteca.exception.NotFoundException;
import com.api.biblioteca.model.Miembro;
import com.api.biblioteca.repository.MiembroRepository;
import com.api.biblioteca.service.MiembroService;
import com.api.biblioteca.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MiembroServiceImpl implements MiembroService {
    
    private final MiembroRepository miembroRepository;
    
    @Override
    public List<Miembro> listarMiembros() {
        
        List<Miembro> miembros = miembroRepository.findAll();

        if (miembros.isEmpty()) {
            throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "miembros");
        }

        return miembros;
    }

    @Override
    public Optional<Miembro> listarMiembroPorId(Integer id) {

        Optional<Miembro> miembroId = miembroRepository.findById(id);

        if (miembroId.isEmpty()){
            throw new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id);
        }

        return miembroId;
    }

    @Override
    public Miembro guardarMiembro(Miembro miembro) {

        boolean existeMiembro = miembroRepository.existsByNumeroIdentificacion(miembro.getNumeroIdentificacion());

        if (existeMiembro) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el miembro");
        }

        miembro.setFechaCreacion(LocalDate.now());
        miembro.setFechaActualizacion(LocalDate.now());

        return miembroRepository.save(miembro);
    }

    @Override
    public Miembro actualizarMiembro(Integer id, Miembro miembroNuevo) {

        Miembro miembroActual = miembroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        boolean existeMiembro = miembroRepository.existsByNumeroIdentificacion(miembroNuevo.getNumeroIdentificacion());
        boolean cambioNumero = !miembroNuevo.getNombre().equals(miembroActual.getNombre());

        if (cambioNumero && existeMiembro) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el miembro");
        }

        miembroActual.setNombre(miembroNuevo.getNombre());
        miembroActual.setApellido(miembroNuevo.getApellido());
        miembroActual.setFechaActualizacion(LocalDate.now());

        return miembroRepository.save(miembroActual);
    }

    @Override
    public void eliminarMiembro(Integer id) {

        Miembro miembro = miembroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        miembroRepository.delete(miembro);
    }
}
