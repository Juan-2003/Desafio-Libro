package com.example.desafioLiteratura.dto;

import com.example.desafioLiteratura.model.Libro;
import jakarta.persistence.ManyToOne;

import java.util.List;

public record AutorDTO(
        String nombre,
        String fechaNacimiento,
        String fechaDefuncion,
        String libros
) {
    public AutorDTO{

    }
}
