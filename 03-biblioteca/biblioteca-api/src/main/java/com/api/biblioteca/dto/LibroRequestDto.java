package com.api.biblioteca.dto;

import lombok.Data;

@Data
public class LibroRequestDto {

    private String titulo;
    private String isbn;
    private Integer autorId;
}
