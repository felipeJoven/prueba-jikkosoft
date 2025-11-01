package com.api.blog.controller;

import com.api.blog.service.PublicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("publicacion")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PublicacionController {

    private final PublicacionService publicacionService;
}
