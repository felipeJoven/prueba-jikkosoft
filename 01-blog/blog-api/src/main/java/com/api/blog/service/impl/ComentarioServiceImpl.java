package com.api.blog.service.impl;

import com.api.blog.repository.ComentarioRepository;
import com.api.blog.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
}
