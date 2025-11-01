package com.api.biblioteca.service.impl;

import com.api.biblioteca.exception.NotFoundException;
import com.api.biblioteca.model.TipoIdentificacion;
import com.api.biblioteca.repository.TipoIdentificacionRepository;
import com.api.biblioteca.service.TipoIdentificacionService;
import com.api.biblioteca.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoIdentificacionServiceImpl implements TipoIdentificacionService {

    private final TipoIdentificacionRepository repository;


    @Override
    public List<TipoIdentificacion> listarTipos() {

        List<TipoIdentificacion> tipos = repository.findAll();

        if (tipos.isEmpty()) {
            throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "tipos de identificaciones");
        }

        return tipos;
    }
}
