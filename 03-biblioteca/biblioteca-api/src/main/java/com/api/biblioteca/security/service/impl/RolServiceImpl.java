package com.api.biblioteca.security.service.impl;

import com.api.biblioteca.exception.NotFoundException;
import com.api.biblioteca.security.model.Rol;
import com.api.biblioteca.security.repository.RolRepository;
import com.api.biblioteca.security.service.RolService;
import com.api.biblioteca.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Override
    public List<Rol> listarRoles() {

        List<Rol> roles = rolRepository.findAll();

        if (roles.isEmpty()) {
            throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "roles");
        }

        return roles;
    }
}
