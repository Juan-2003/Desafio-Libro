package com.example.desafioLiteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResultados(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<ApiAutores> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer descargas

) {
}
