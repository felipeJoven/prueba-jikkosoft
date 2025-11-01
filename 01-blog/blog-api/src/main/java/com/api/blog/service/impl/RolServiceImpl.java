package com.api.blog.service.impl;

import com.api.blog.repository.RolRepository;
import com.api.blog.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository repository;
}
