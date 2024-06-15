package com.example.desafioLiteratura.dto;

import com.example.desafioLiteratura.model.Autores;
import com.example.desafioLiteratura.model.Idiomas;
import com.example.desafioLiteratura.model.Libro;
import jakarta.persistence.*;

import java.util.List;

public record LibroDTO(
         String titulo,
         Idiomas idioma,
         Integer descargas,
         String autores
) {
    public LibroDTO{}
}
