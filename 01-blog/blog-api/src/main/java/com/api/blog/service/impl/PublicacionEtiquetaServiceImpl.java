package com.api.blog.service.impl;

import com.api.blog.repository.PublicacionRepository;
import com.api.blog.service.PublicacionEtiquetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicacionEtiquetaServiceImpl implements PublicacionEtiquetaService {

    private final PublicacionRepository publicacionRepository;
}
