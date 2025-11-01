package com.api.blog.service.impl;

import com.api.blog.repository.PublicacionRepository;
import com.api.blog.service.PublicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicacionServiceImpl implements PublicacionService {

    private final PublicacionRepository publicacionRepository;
}
