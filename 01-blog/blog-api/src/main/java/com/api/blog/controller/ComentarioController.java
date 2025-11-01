package com.api.blog.controller;

import com.api.blog.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comentario")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;
}
