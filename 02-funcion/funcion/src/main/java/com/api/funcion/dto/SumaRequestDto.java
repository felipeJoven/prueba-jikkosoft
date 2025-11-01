package com.api.funcion.dto;

import lombok.Data;

import java.util.List;

@Data
public class SumaRequestDto {

    private List<Integer> numeros;
    private Integer objetivo;
}
