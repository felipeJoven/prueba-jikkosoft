package com.api.blog.controller;

import com.api.blog.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rol")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;
}
