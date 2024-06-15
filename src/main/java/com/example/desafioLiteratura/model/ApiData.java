package com.example.desafioLiteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiData(
        @JsonAlias("results") List<ApiResultados> resultados
) {
}
