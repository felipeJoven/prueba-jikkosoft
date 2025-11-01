package com.api.blog.service.impl;

import com.api.blog.repository.RecuperarClaveRepository;
import com.api.blog.service.RecuperarClaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecuperarClaveServiceImpl implements RecuperarClaveService {

    private final RecuperarClaveRepository recuperarClaveRepository;
}
