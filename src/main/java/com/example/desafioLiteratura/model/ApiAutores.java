package com.example.desafioLiteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiAutores(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String fechaNacimiento,
        @JsonAlias("death_year") String fechaDefuncion

) {
}
