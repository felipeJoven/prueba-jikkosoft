package com.api.blog.service.impl;

import com.api.blog.repository.EtiquetaRepository;
import com.api.blog.service.EtiquetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EtiquetaServiceImpl implements EtiquetaService {

    private final EtiquetaRepository etiquetaRepository;
}
