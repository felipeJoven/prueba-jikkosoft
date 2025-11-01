package com.api.biblioteca.service.impl;

import com.api.biblioteca.repository.RecuperarClaveRepository;
import com.api.biblioteca.service.RecuperarClaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecuperarClaveServiceImpl implements RecuperarClaveService {

    private final RecuperarClaveRepository recuperarClaveRepository;
}
